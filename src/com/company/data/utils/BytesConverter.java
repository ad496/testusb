package com.company.data.utils;

import org.apache.commons.lang.SerializationUtils;

import java.io.Serializable;
import java.util.Base64;

/**
 * Вспомогательный класс преобразования объектов в массивы байтов и обратно
 */
public class BytesConverter {

    /**
     * Преобразование массива байт в объект
     *
     * @param <T>  тип параметра
     * @param data массив байт для преобразования
     * @param type тип (класс) объекта, в который происходит преобразования
     * @return объект типа (класса) type
     */
    @SuppressWarnings("unchecked")
    public static <T extends Serializable> T encodeToObject(byte[] data, Class<T> type) {
        return (T) SerializationUtils.deserialize(data);
    }

    /**
     * Преобразование объекта в массив байт
     *
     * @param <T>  тип параметра
     * @param data объект для преобразования
     * @return массив байт полученного объекта
     */
    public static <T extends Serializable> byte[] decodeFromObject(T data) {
        return SerializationUtils.serialize(data);
    }

    /**
     * Преобразование массива байт в Base64 строку
     *
     * @param data массив байт для преобразования
     * @return Base64 строка с данными
     */
    public static String encodeToBase64String(byte[] data) {
        return Base64.getEncoder().encodeToString(data);
    }

    /**
     * Преобразование Base64 строки в массив байт
     *
     * @param data Base64 строка с данными
     * @return массив байт
     */
    public static byte[] decodeFromBase64String(String data) {
        return Base64.getDecoder().decode(data);
    }
}
