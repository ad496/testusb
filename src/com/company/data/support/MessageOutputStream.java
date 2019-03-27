package com.company.data.support;

import com.google.common.io.LittleEndianDataOutputStream;

import java.io.ByteArrayOutputStream;

/**
 * Вспомогательный класс для формирования массива байт
 */
public class MessageOutputStream extends LittleEndianDataOutputStream {

    private final ByteArrayOutputStream stream;

    public MessageOutputStream(ByteArrayOutputStream stream) {
        super(stream);
        this.stream = stream;
    }

    public static MessageOutputStream ofSize(int initialSize) {
        return new MessageOutputStream(new ByteArrayOutputStream(initialSize));
    }

    public static MessageOutputStream defaultSize() {
        return new MessageOutputStream(new ByteArrayOutputStream());
    }

    public byte[] toByteArray() {
        return stream.toByteArray();
    }

}
