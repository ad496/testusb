package com.company.data.support;

/**
 * Вспомогательный класс записи числа в массив из 2-х байтов
 */
public class Int16LE {
    private final byte[] bytes;

    public Int16LE(int num) {
        this.bytes = new byte[2];
        bytes[0] = (byte) (0xFF & num);
        bytes[1] = (byte) (0xFF & (num >> 8));
    }

    public byte[] bytes() {
        return bytes;
    }
}
