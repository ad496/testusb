package com.company;

import java.nio.ByteBuffer;
import java.util.Arrays;

public class ByteReader {
   final byte[] bytes;
   final static int ALLOCATE_VALUE=1000000;
   private int messageCode;
   private int stx;
   private int messageLength;
   private byte[] dataBody;
   private Object responce;


    public ByteReader(byte[] bytes){
        this.bytes = bytes;
       refreshHeader();
    }

    public ByteReader(ByteBuffer buffer) {
        ByteBuffer bb =ByteBuffer.allocate(ALLOCATE_VALUE);
        bb.put(buffer);
        byte[] b = new byte[0];
        if(bb.hasArray()) {
            b = bb.array();
        } else {
            b = new byte[bb.remaining()];
            bb.get(b);
        }
        this.bytes=b;
        refreshHeader();
    }

    private void refreshHeader(){
        this.messageCode=new Byte(this.bytes[3]).intValue();
        System.out.println("message code - "+this.messageCode);
        this.stx=new Byte(this.bytes[0]).intValue();
        this.messageLength=Utils.unsignedIntToInt(this.bytes[1],this.bytes[2]);
        this.dataBody= Arrays.copyOfRange(bytes, 4, 4+messageLength-3);
        responce=FactoryResponveKkm.getObject(dataBody,messageCode);
    }

    public int getStx(){
        return stx;
    }
    public int getMessageLength(){
        return messageLength;
    }

    public int getMessageCode(){
        return messageCode;
    }



    public Object getResponce() {
        return responce;
    }
}
