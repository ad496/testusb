package com.company.request;

import com.company.Utils;
import com.company.data.support.CRC16;
import com.company.data.support.Int16LE;

import java.io.IOException;
import java.lang.reflect.Field;
import java.nio.ByteBuffer;
import java.util.*;

public class BuilderRequest {

    private BaseRequest o;

    public BuilderRequest(BaseRequest o){

        this.o = o;
    }

    public ByteBuffer build() {

        int length=1+2;
        if(o.messageData!=null){
            length=length+o.messageData.length;
        }
        int alocateValue=6;
        if(o.messageData!=null){
            alocateValue=alocateValue+o.messageData.length;
        }

        if(o instanceof TestKkt){
            byte[] sd= new byte[0];
            try {
                sd = ((TestKkt)o).getByte();
            } catch (IOException e) {
                e.printStackTrace();
            }
            ByteBuffer buffer = ByteBuffer.allocateDirect(sd.length);
            buffer.put(sd);
            return buffer;

        }else {
            ByteBuffer buffer = ByteBuffer.allocateDirect(alocateValue);
            buffer.put((byte) 0x02);
            buffer.put(new Int16LE(length).bytes());// длина
            buffer.put(o.codeMessage);// данные

            CRC16 crc16 = new CRC16();
            crc16.update(new Int16LE(length).bytes());
            crc16.update(new Byte(o.codeMessage).intValue());
//        if(o.messageData!=null){
//            for (byte m : o.messageData) {
//                crc16.update(new Byte(m).intValue());
//            }
//            crc16.update(o.messageData);
//        }
            buffer.put(new Int16LE(crc16.checksum()).bytes());
            return buffer;
        }







    }


    private <T> List<Field> getFields(T t) {
        List<Field> fields = new ArrayList<>();
        Class clazz = t.getClass();
        while (clazz != Object.class) {
            fields.addAll(Arrays.asList(clazz.getDeclaredFields()));
            clazz = clazz.getSuperclass();
        }
        return fields;
    }

    class TempItemTlv{
        public Field field;
        public TlvItemAnnotation annotation;
        public TempItemTlv(Field field,TlvItemAnnotation annotation){

            this.field = field;
            this.annotation = annotation;
        }
    }
}
