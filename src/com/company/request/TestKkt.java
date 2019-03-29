package com.company.request;

import com.company.data.support.CRC16;
import com.company.data.support.Int16LE;
import com.google.common.io.ByteArrayDataOutput;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class TestKkt extends BaseRequest {

    public TestKkt(){
        this.codeMessage=(byte) 101;
        this.messageData=new Int16LE(0).bytes();
    }

    private void insert( List<Byte> bytes,byte[]b){
        bytes.add(0,b[1]);
        bytes.add(0,b[0]);
    }

    public byte[] getByte() throws IOException {

        List<Byte> bytes=new ArrayList<>();



        //код сообшения
        bytes.add((byte) 0x65);

        //данные сообщения
        for (byte aByte : new Int16LE(0).bytes()) {
            bytes.add(aByte);
        }
        // узнаем длину
        int l=bytes.size()+2;
        // в инсерим в лист
        insert(bytes,new Int16LE(l).bytes());

        // узнаем контрольную сумму инсерим в лист
        CRC16 c16=new CRC16();
        c16.update(bytes);
        int ii=c16.checksum();
        for (byte aByte : new Int16LE(ii).bytes()) {
            bytes.add(aByte);
        }

        //инсертив метку начаоа пае=кета
        bytes.add(0,(byte)0x02);
        byte[] res=new byte[bytes.size()];
        for (int i = 0; i < bytes.size(); i++) {
            res[i]=bytes.get(i);
        }

        return res;





//        byte[] s=new byte[8];
//        s[0]=0x02;
//        byte[] длина=new Int16LE(5).bytes();
//        s[1]=длина[0];
//        s[2]=длина[1];
//        byte коДсообщения=(byte)0x65;
//        s[3]=коДсообщения;
//        byte[] номертеста=new  Int16LE(0).bytes();
//        s[4]=номертеста[0];
//        s[5]=номертеста[1];
//        CRC16 crc16 = new CRC16();
//        crc16.update(new byte[]{s[1],s[2],s[3],s[4],s[5]});
//        int i=crc16.checksum();
//        byte[] crs=new  Int16LE(i).bytes();
//        s[6]=crs[0];
//        s[7]=crs[1];
//        return s;
    }
}
