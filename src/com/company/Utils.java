package com.company;


import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Utils {

    public static int getBitPos(byte b,int pos){
        return (b >> pos) & 1;
    }

    public static final int unsignedIntToInt(byte a,byte b)
    {
        long l = 0;
        l |= b & 0xFF;
        l <<= 8;
        l |= a & 0xFF;
        return (int) l;
    }
    public static int byteToInt(byte b){
        return new Byte(b).intValue();
    }

    public static String getStringASCII(byte[] bytes){
        return new String(bytes, Charset.forName("CP866")).trim();
    }

    public static double fromFVLN(byte[] data) throws Exception {
        if (data[0] > 4) {
            throw new Exception("Неверная длина FVLN");
        }
        if (data.length < 2) {
            throw new Exception("Неверная длина FVLN");
        }
        byte[] d = new byte[data.length - 1];
        System.arraycopy(data, 1, d, 0, d.length);
        double result = toInt(d);
        int power = data[0];
        for (int i = 0; i < power; i++) {
            result = result / 10;
        }
        return result;
    }

    public static long toInt(byte[] d) {
        long result = 0;
        for (int i = d.length - 1; i >= 0; i--) {
            result <<= 8;
            result |= d[i] & 0xFF;
        }
        return result;
    }

    public float toFVLN( byte[] arr) {

        return new VariableLengthNumber(Arrays.copyOfRange(arr, 1, arr.length)).getValue();
    }

    private class VariableLengthNumber {
        private final long value;

        public VariableLengthNumber(byte[] array) {
            long result = 0L;
            if (array.length != 0) {
                result = (((long) array[array.length - 1] & 0xff) << (array.length - 1) * 8);
                for (int i = array.length - 2; i > -1; i--) {
                    result = result | (((long) array[i] & 0xff) << (i) * 8);
                }
            }

            this.value = result;

        }

        public long getValue() {
            return value;
        }
    }


}
