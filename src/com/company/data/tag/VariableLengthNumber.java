package com.company.data.tag;

/**
 * Реализация типа тэга VLN
 */
class VariableLengthNumber {
    private final long value;

    VariableLengthNumber(byte[] array) {
        long result = 0L;
        if (array.length != 0) {
            result = (((long) array[array.length - 1] & 0xff) << (array.length - 1) * 8);
            for (int i = array.length - 2; i > -1; i--) {
                result = result | (((long) array[i] & 0xff) << (i) * 8);
            }
        }

        this.value = result;

    }

    long getValue() {
        return value;
    }
}
