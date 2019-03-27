package com.company.data.tag;

import com.google.common.io.BaseEncoding;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.ByteArrayInputStream;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static java.nio.ByteOrder.LITTLE_ENDIAN;

/**
 * Тег с данными ККТ, включая вспомогательные классы по преобразованию данных
 */
public class Tag {
    private static final Logger LOGGER = LoggerFactory.getLogger(Tag.class);

    private final FiscalDocumentTag tagInfo;
    private final ByteBuffer binary;
    private final BaseEncoding encoding = BaseEncoding.base16();

    public Tag(FiscalDocumentTag tagInfo, ByteBuffer binary) {
        this.tagInfo = tagInfo;
        this.binary = binary;
    }

    public FiscalDocumentTag getTagInfo() {
        return tagInfo;
    }

    public ByteBuffer getBinary() {
        return binary;
    }

    public Object readValue() {
        return readValue(tagInfo.getInType());
    }

    public Object readValue(TagInType tagInType) {
        switch (tagInType) {
            case BYTE:
                return readByteUnsigned();
            case INT32LE:
                return readIntUnsigned();
            case INT16LE:
                return readShortUnsigned();
            case STLV:
                return readTags();
            case UNIX_TIME:
                return readIntUnsigned();
            case VLN:
                return readVariableLengthNumber();
            case FVLN:
                return readFloatVariableLengthNumber();
            case ASCII:
                return readAscii();
            case UNKNOWN:
                return readHex();
            case BYTE_ARRAY:
                return encoding.encode(binary.array());
            default:
                return encoding.encode(binary.array());
        }
    }

    public Object readHex() {
        return encoding.encode(binary.array());
    }

    public String readAscii() {
        return new String(binary.array(), Charset.forName("CP866"));
    }

    public long readVariableLengthNumber() {
        return new VariableLengthNumber(binary.array()).getValue();
    }

    public float readFloatVariableLengthNumber() {
        byte[] arr = binary.array();
        return new VariableLengthNumber(Arrays.copyOfRange(arr, 1, arr.length)).getValue();
    }

    public List<TagBuffer> readTags() {
        TagReader reader = new TagReader(new ByteArrayInputStream(binary.array()));
        try {
            return reader.readTagBuffers();

        } catch (Exception e) {

        }
        return Collections.emptyList();
    }

    public long readShortUnsigned() {
        return binary.order(LITTLE_ENDIAN).getInt() & 0xffffffffl;
    }

    public long readIntUnsigned() {
        return binary.order(LITTLE_ENDIAN).getInt() & 0xffffffffl;
    }

    public int readByteUnsigned() {
        return binary.position() < binary.limit() ? binary.get() & 0xFF : 0;
    }

    public long readNumberValue() {
        Object number = readValue();
        if (number instanceof Number) {
            return ((Number) number).longValue();
        } else {
            LOGGER.error("{} tag is not a number", tagInfo.getId());
            return -1;
        }
    }
}
