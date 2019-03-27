package com.company;

public class ByteReader {
   final byte[] bytes;
    public ByteReader(byte[] bytes){
        this.bytes = bytes;
    }
    public int STX(){
        return new Byte(this.bytes[0]).intValue();
    }
    public int messageLength(){
        return unsignedIntToInt(this.bytes[1],this.bytes[2]);
    }

    public int messageCode(){
        return new Byte(this.bytes[3]).intValue();
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
}
