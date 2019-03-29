package com.company.data.support;

import java.util.List;

/**
 * Создание контрольной суммы
 */
public class CRC16 {

    private final static int polynomial = 0x1021;
    private int crc = 0xffff;

    public int checksum() {
        return crc &= 0xffff;
    }

    public void update(int b) {
        for (int i = 0; i < 8; i++) {
            boolean bit = ((b >> (7 - i) & 1) == 1);
            boolean c15 = ((crc >> 15 & 1) == 1);
            crc <<= 1;

            if (c15 ^ bit)
                crc ^= polynomial;
        }
    }

    public void update(byte[] array) {
        for (byte anArray : array) {
            update(anArray);
        }
    }

    public void update(List<Byte> array) {
        for (byte anArray : array) {
            update(anArray);
        }
    }
}
