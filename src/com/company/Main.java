package com.company;

import com.company.data.document.OpenShift;
import com.company.data.support.CRC16;
import com.company.data.support.Int16LE;
import com.company.data.utils.BytesConverter;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import org.usb4java.*;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.IntBuffer;
import java.nio.charset.Charset;


public class Main {

    public static void main(String[] args) {



        final Context context = new Context();
        int result = LibUsb.init(context);
        if (result < 0)
        {
            throw new LibUsbException("Unable to initialize libusb", result);
        }
        final DeviceList list = new DeviceList();
        result = LibUsb.getDeviceList(context, list);
        if (result < 0)
        {
            throw new LibUsbException("Unable to get device list", result);
        }
        try
        {
            DeviceHandle handle = new DeviceHandle();
            for (Device device: list)
            {
                DeviceDescriptor descriptor = new DeviceDescriptor();
                result = LibUsb.getDeviceDescriptor(device, descriptor);
                if (result != LibUsb.SUCCESS) throw new LibUsbException("Unable to read device descriptor", result);
                if(descriptor.idVendor()==1155){

                    result = LibUsb.open(device, handle);
                    if (result != LibUsb.SUCCESS) throw new LibUsbException("Unable to open USB device", result);
                }

                System.out.println(descriptor.idVendor()+" "+ descriptor.idProduct());
            }



            {
                ByteBuffer buffer = ByteBuffer.allocateDirect(6);


                //1 STX Byte 1 = 02H
                //2 Длина сообщения  2 Uint16,LE
                //3 Код сообщения Byte = 45H
                //4 CRC

                //= 1 (Код сообщения + N (Данные сообщения) + 2 (CRC)

                buffer.put((byte) 0x02);
                buffer.put(new Int16LE(3).bytes());// длина
                buffer.put((byte) 0x45);// данные
               // buffer.put(new Int16LE(0).bytes());// номер теста

                CRC16 crc16 = new CRC16();
                crc16.update(new Int16LE(3).bytes());
                crc16.update(new byte[]{(byte) 0x45});
                buffer.put(new Int16LE(crc16.checksum()).bytes());




                IntBuffer transfered = IntBuffer.allocate(1);
                result = LibUsb.bulkTransfer(handle, (byte) 0x02, buffer, transfered, 1000);
                if (result != LibUsb.SUCCESS) throw new LibUsbException("Control transfer failed",result);
                System.out.println(transfered.get() + " bytes sent");
            }


            ByteBuffer buffer = BufferUtils.allocateByteBuffer(100).order( ByteOrder.nativeOrder());

            IntBuffer transferred = BufferUtils.allocateIntBuffer();
             result = LibUsb.bulkTransfer(handle, (byte) 0x81, buffer,
                    transferred, 600000);
            if (result != LibUsb.SUCCESS)
            {
                throw new LibUsbException("Unable to read data", result);
            }

//            int f=transferred.get();
            ByteBuffer bb =ByteBuffer.allocate(1000);
            bb.put(buffer);
            byte[] bytes = new byte[0];
            try {

                if(bb.hasArray()) {
                    bytes = bb.array();
                } else {
                    bytes = new byte[bb.remaining()];
                    bb.get(bytes);
                }

            } catch (Exception e) {
                e.printStackTrace();
            }

            ByteReader  byteReader=new ByteReader(bytes);

            byte code=bytes[0];
            System.out.println("STX - "+byteReader.STX());
            System.out.println("Длина сообщения - "+byteReader.messageLength());
            System.out.println("Код сообщения - "+byteReader.messageCode());
            System.out.println("Текущий статус ккт "+String.format("%8s", Integer.toBinaryString(bytes[4] & 0xFF)).replace(' ', '0'));
            System.out.println("Текущий статус ккт "+String.format("%8s", Integer.toBinaryString(bytes[5] & 0xFF)).replace(' ', '0'));

            System.out.println("Результат выполнения команды - "+unsignedIntToLong(bytes[6],bytes[7]));
            System.out.println("Текущий статус принтера "+String.format("%8s", Integer.toBinaryString(bytes[8] & 0xFF)).replace(' ', '0'));
            byte[] d=new byte[]{
                    bytes[9],
                    bytes[10],
                    bytes[11],
                    bytes[12],
                    bytes[13],
                    bytes[14],
                    bytes[15],
                    bytes[16],
                    bytes[17],
                    bytes[18],
                    bytes[19],
                    bytes[20],
                    bytes[21],
                    bytes[22],
                    bytes[23],
                    bytes[24],
                    bytes[25],
                    bytes[26],
                    bytes[27],
                    bytes[28],};
            System.out.println("Серийный номер ККТ - "+new String(d, Charset.forName("CP866")));
            byte[] d1=new byte[]{
                    bytes[29],
                    bytes[30],
                    bytes[31],
                    bytes[32],
                    bytes[33],
                    bytes[34],
                    bytes[35],
                    bytes[36],
                    bytes[37],
                    bytes[38],

                    bytes[39],
                    bytes[40],
                    bytes[41],
                    bytes[42],
                    bytes[43],
                    bytes[44],
                    bytes[45],
                    bytes[46],
                    bytes[47],
                    bytes[48],

                    bytes[49],
                    bytes[50],
                    bytes[51],
                    bytes[52],
                    bytes[53],
                    bytes[54],
                    bytes[55],
                    bytes[56],
                    bytes[57],
                    bytes[58]};
            System.out.println("Модель - "+new String(d1, Charset.forName("CP866")));
            byte[] d2=new byte[]{
                    bytes[59],
                    bytes[60],
                    bytes[61],
                    bytes[62],
                    bytes[63],
                    bytes[64],
                    bytes[65],
                    bytes[66],
                    bytes[67],
                    bytes[68],

                    bytes[69],
                    bytes[70],
                    bytes[71],
                    bytes[72],
                    bytes[73],
                    bytes[74],
                    bytes[75],
                    bytes[76],
                    bytes[77],
                    bytes[78],

                    bytes[79],
                    bytes[80],
                    bytes[81],
                    bytes[82],
                    bytes[83],
                    bytes[84],
                    bytes[85],
                    bytes[86],
                    bytes[87],
                    bytes[88]};
            System.out.println("Производитель - "+new String(d2, Charset.forName("CP866")));
            System.out.println("Версия ПО - "+unsignedIntToLong(bytes[89],bytes[90]));
            System.out.println("Номер сборки ПО - "+ unsignedIntToLong(bytes[91],bytes[92]));
            System.out.println("CRC - "+ unsignedIntToLong(bytes[93],bytes[94]));

            System.out.println(transferred.get()+ " bytes read from device");
        }
        finally
        {
            LibUsb.freeDeviceList(list, true);
        }
        LibUsb.exit(context);


    }

    public static final long unsignedIntToLong(byte a,byte b)
    {
        long l = 0;
        l |= a & 0xFF;
        l <<= 8;
        l |= b & 0xFF;
        return l;
    }
    public static int byteToInt(byte b){
        return new Byte(b).intValue();
    }

}


