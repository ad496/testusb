package com.company.data.tag;

import com.company.data.document.FiscalDocument;
import com.company.data.tag.annotation.DocumentType;
import com.company.data.tag.annotation.DocumentTypes;
import com.company.data.tag.annotation.PropertyTag;
import com.google.common.base.Preconditions;
import com.google.common.collect.*;
import com.google.common.io.CountingInputStream;
import com.google.common.io.LittleEndianDataInputStream;
import org.apache.commons.lang.ArrayUtils;
import org.reflections.Reflections;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.ParameterizedType;
import java.nio.ByteBuffer;
import java.text.ParseException;
import java.util.*;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * Конструктор объектов типа FiscalDocument на основании полученных бинарных данных ККТ
 */
public class FiscalDocumentMapper {
    private final static Logger LOGGER = LoggerFactory.getLogger(FiscalDocumentMapper.class);

    private final Set<Class<?>> documentTypes;

    public FiscalDocumentMapper(String[] documentPackages) {
        Reflections reflections = new Reflections((Object[]) documentPackages);
        this.documentTypes = reflections.getTypesAnnotatedWith(DocumentType.class);
        this.documentTypes.addAll(reflections.getTypesAnnotatedWith(DocumentTypes.class));
    }

    /**
     * Преобразовать декодированный ПКЗ массив байт в объект типа FiscalDocument
     *
     * @param rawData         декодированный ПКЗ массив байт
     * @param header          декодированный заголовок ПКЗ массива байт документа
     * @return объект типа FiscalDocument
     * @throws ParseException         в случае ошибок создания объекта из массива тэгов
     * @throws IOException            в случае ошибок преобразования rawData в массив тэгов
     * @throws InstantiationException в случае ошибки нахождения класса документа запрощенного типа
     * @throws IllegalAccessException в случае ошибок создания объекта из массива тэгов
     */
    public FiscalDocument read(byte[] rawData, byte[] header) throws ParseException, IOException, InstantiationException, IllegalAccessException {
        ByteArrayInputStream arrayInputStream = new ByteArrayInputStream(rawData);
        CountingInputStream countingInputStream = new CountingInputStream(arrayInputStream);
        LittleEndianDataInputStream stream = new LittleEndianDataInputStream(countingInputStream);

        int documentCode = stream.readUnsignedShort();
        int documentLength = stream.readUnsignedShort();
        int lastDocumentByte = (int) countingInputStream.getCount() + documentLength;

        ListMultimap<Integer, ByteBuffer> tagsRawData = ArrayListMultimap.create();
        TagReader reader = new TagReader(stream);
        while (countingInputStream.getCount() < lastDocumentByte) {
            TagBuffer tagBuffer = reader.readTagBuffer();
            tagsRawData.put(tagBuffer.getTagId(), tagBuffer.getTagValue());
        }

        Preconditions.checkState(stream.read() == -1, "Nothing left in container");

        String protocolVersion = readProtocolVersion(header, documentCode);

        //ListMultimap<Integer, Tag> tagsMap = transformEntries(tagsRawData, (t, b) -> new Tag(FiscalDocumentTag.forValue(t), b));
        ListMultimap<Integer, Tag> tagsMap = ArrayListMultimap.create();
        Collection<Map.Entry<Integer, ByteBuffer>> tagsEntries = tagsRawData.entries();
        for (Map.Entry tagEntry : tagsEntries) {
            Integer tagId = (Integer) tagEntry.getKey();
            ByteBuffer tagValue = (ByteBuffer) tagEntry.getValue();
            try {
                tagsMap.put(tagId, new Tag(FiscalDocumentTag.forValue(tagId), tagValue));
            }
            catch (IllegalArgumentException e) {
                LOGGER.error("Can't create tag: Unknown FiscalDocumentTag code  " + tagId);
            }
        }

        Class documentType = null;
        for (Class<?> type : documentTypes) {
            DocumentType[] annotations = type.getAnnotationsByType(DocumentType.class);
            for (DocumentType annotation : annotations) {
                if (annotation.value() == documentCode && (protocolVersion == null || (annotation.protocolVersion() != null && ArrayUtils.contains(annotation.protocolVersion(), protocolVersion)))) {
                    documentType = type;
                    break;
                }
            }

            if (documentType != null) {
                break;
            }
        }

        if (documentType == null) {
            for (Class<?> type : documentTypes) {
                DocumentType[] annotations = type.getAnnotationsByType(DocumentType.class);
                for (DocumentType annotation : annotations) {
                    if (annotation.value() == documentCode && (annotation.protocolVersion() == null ||  annotation.protocolVersion().length == 0)) {
                        documentType = type;
                        break;
                    }
                }

                if (documentType != null) {
                    break;
                }
            }
        }

        if (documentType == null) {
            throw new InstantiationException("Unable to find applicable document type for documentCode =" + documentCode);
        }

        Collection<Exception> exceptions = new ConcurrentLinkedQueue<>();
        FiscalDocument document = (FiscalDocument) createObjectFromTags(tagsMap, documentType, protocolVersion, exceptions);

        if (!exceptions.isEmpty()) {
            ParseException parseException = new ParseException("Unable to parse tags", 0);
            for (Exception ex : exceptions) {
                parseException.addSuppressed(ex);
            }

            throw parseException;
        }

        document.setProtocolVersion(protocolVersion);
        document.setDocumentCode(documentCode);
        document.setRawData(rawData);

        return document;
    }

    private String readProtocolVersion(byte[] header, int documentCode) throws IOException {
        if (header == null || header.length < 3) {
            return null;
        }

        int messageCode = header[0];
        if (messageCode != -91 && messageCode != 90) { //A5 and 5A
            throw new IOException("Wrong message type in header " + messageCode);
        }

        int headerDocumentCode = header[1];
        if (headerDocumentCode != documentCode) {
            throw new IOException("Wrong document code in header " + headerDocumentCode);
        }

        int version = header[2];

        return String.valueOf(version) + ".0";
    }

    private <T> T createObjectFromTags(
            ListMultimap<Integer, Tag> tagsMap,
            Class<T> type,
            String protocolVersion,
            Collection<Exception> exceptions) throws IllegalAccessException, InstantiationException {
        T fiscalDocument = type.newInstance();

        Map<Field, PropertyTag[]> fieldTags = new HashMap<>();
        for (Field field : getAllFields(new ArrayList<>(), fiscalDocument.getClass())) {
            PropertyTag[] tagAnnotations = field.getAnnotationsByType(PropertyTag.class);
            if (tagAnnotations != null && tagAnnotations.length > 0) {
                fieldTags.put(field, tagAnnotations);
            }
        }

        fieldTags.entrySet().stream().forEach(fieldTag -> {
            Field field = fieldTag.getKey();

            PropertyTag[] tagAnnotations = fieldTag.getValue();
            Optional<PropertyTag> tagAnnotationByProtocolVersion = Arrays.stream(tagAnnotations)
                    .filter(a -> protocolVersion == null || (a.protocolVersion() != null && a.protocolVersion().length > 0 && ArrayUtils.contains(a.protocolVersion(), protocolVersion)))
                    .findFirst();

            if (!tagAnnotationByProtocolVersion.isPresent()) {
                tagAnnotationByProtocolVersion = Arrays.stream(tagAnnotations)
                        .filter(a -> a.protocolVersion() == null || a.protocolVersion().length == 0)
                        .findFirst();
            }

            if (tagAnnotationByProtocolVersion.isPresent()) {
                PropertyTag tagAnnotation = tagAnnotationByProtocolVersion.get();
                Object value = getTagValue(tagsMap, tagAnnotation.value());

                if (value != null) {
                    fieldTag.getKey().setAccessible(true);
                    try {
                        if (tagAnnotation.value().getOutType() != TagOutType.TAGS) {
                            field.set(fiscalDocument, value);
                        } else {
                            ParameterizedType listType = (ParameterizedType) field.getGenericType();
                            Class<?> genericListType = (Class<?>) listType.getActualTypeArguments()[0];

                            List<Tag> tagList = (List<Tag>) value;
                            field.set(fiscalDocument, getTagListValue(tagList, genericListType, tagAnnotation.listItemTag(), protocolVersion, exceptions));
                        }
                    } catch (Exception ex) {
                        exceptions.add(ex);
                    }
                }
            }
        });

        Map<Method, PropertyTag[]> methodTags = new HashMap<>();
        for (Method method : getAllMethods(new ArrayList<>(), fiscalDocument.getClass())) {
            if (Modifier.isPublic(method.getModifiers())
                    && method.getParameterTypes().length == 1
                    && method.getName().startsWith("set")) {

                PropertyTag[] tagAnnotations = method.getAnnotationsByType(PropertyTag.class);
                if (tagAnnotations != null && tagAnnotations.length > 0) {
                    methodTags.put(method, tagAnnotations);
                }
            }
        }

        methodTags.entrySet().stream().forEach(methodTag -> {
            Method method = methodTag.getKey();
            PropertyTag[] tagAnnotations = methodTag.getValue();
            Optional<PropertyTag> tagAnnotationByProtocolVersion = Arrays.stream(tagAnnotations)
                    .filter(a -> protocolVersion == null || (a.protocolVersion() != null && a.protocolVersion().length > 0 && ArrayUtils.contains(a.protocolVersion(), protocolVersion)))
                    .findFirst();

            if (!tagAnnotationByProtocolVersion.isPresent()) {
                tagAnnotationByProtocolVersion = Arrays.stream(tagAnnotations)
                        .filter(a -> a.protocolVersion() == null || a.protocolVersion().length == 0)
                        .findFirst();
            }
            if (tagAnnotationByProtocolVersion.isPresent()) {
                PropertyTag tagAnnotation = tagAnnotationByProtocolVersion.get();
                Object value = getTagValue(tagsMap, tagAnnotation.value());
                if (value != null) {
                    try {
                        if (tagAnnotation.value().getOutType() != TagOutType.TAGS) {
                            method.invoke(fiscalDocument, value);
                        } else {
                            ParameterizedType listType = (ParameterizedType) method.getGenericParameterTypes()[0];
                            Class<?> genericListType = (Class<?>) listType.getActualTypeArguments()[0];

                            List<Tag> tagList = (List<Tag>) value;
                            method.invoke(fiscalDocument, getTagListValue(tagList, genericListType, tagAnnotation.listItemTag(), protocolVersion, exceptions));
                        }
                    } catch (Exception ex) {
                        exceptions.add(ex);
                    }
                }
            }
        });

        return fiscalDocument;
    }

    private <T> List<T> getTagListValue(
            List<Tag> tags,
            Class<T> type,
            FiscalDocumentTag listItemTag,
            String protocolVersion,
            Collection<Exception> exceptions) throws IllegalAccessException, InstantiationException {
        List<T> values = new ArrayList<>();

        for (Tag tag : tags) {
            ListMultimap<Integer, Tag> subTagsMap = ArrayListMultimap.create();
            //tag.readTags()
            //        .stream()
            //        .map(tagBuffer -> new Tag(FiscalDocumentTag.forValue(tagBuffer.getTagId()), tagBuffer.getTagValue()))
            //        .forEach(t -> subTagsMap.put(t.getTagInfo().getId(), t));
            for (TagBuffer tagBuffer : tag.readTags()) {
                Integer tagId = tagBuffer.getTagId();
                ByteBuffer tagValue = tagBuffer.getTagValue();
                try {
                    subTagsMap.put(tagId, new Tag(FiscalDocumentTag.forValue(tagId), tagValue));
                }
                catch (IllegalArgumentException e) {
                    LOGGER.error("Can't create subtag with id: Unknown FiscalDocumentTag code " + tagId);
                }

            }

            T value = listItemTag == null || listItemTag == FiscalDocumentTag.DEFAULT ?
                    createObjectFromTags(subTagsMap, type, protocolVersion, exceptions) :
                    (T) getTagValue(subTagsMap, listItemTag);

            values.add(value);
        }

        return values;
    }

    private Object getTagValue(ListMultimap<Integer, Tag> tags, FiscalDocumentTag fiscalDocumentTag) {
        Object value = null;
        int tagId = fiscalDocumentTag.getId();
        switch (fiscalDocumentTag.getOutType()) {
            case STRING:
                checkOnlyOneTag(tags, tagId);
                Object o = tags.get(tagId).isEmpty() ? "" : tags.get(tagId).get(0).readValue();
                value = String.valueOf(o).trim();
                break;
            case NUMBER:
                checkOnlyOneTag(tags, tagId);
                value = tags.get(tagId).isEmpty() ? 0L : tags.get(tagId).get(0).readNumberValue();
                break;
            case TAGS:
                value = tags.get(tagId);
                break;
            case RAW:
                checkOnlyOneTag(tags, tagId);
                value = tags.get(tagId).isEmpty() ? ByteBuffer.allocate(0) : tags.get(tagId).get(0).getBinary();
                break;
        }

        return value;
    }

    private void checkOnlyOneTag(ListMultimap<Integer, Tag> tags, int tag) {
        Preconditions.checkState(tags.get(tag).size() < 2, "There is more than one tag '%d' in document", tag);
    }

    private static List<Field> getAllFields(List<Field> fields, Class<?> type) {
        fields.addAll(Arrays.asList(type.getDeclaredFields()));

        if (type.getSuperclass() != null) {
            getAllFields(fields, type.getSuperclass());
        }

        return fields;
    }

    private static List<Method> getAllMethods(List<Method> methods, Class<?> type) {
        methods.addAll(Arrays.asList(type.getDeclaredMethods()));

        if (type.getSuperclass() != null) {
            getAllMethods(methods, type.getSuperclass());
        }

        return methods;
    }
}
