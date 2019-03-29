package com.company;

import com.company.request.BuilderRequest;
import com.company.request.InfoKkt;
import com.company.request.RVersionKkt_69;
import org.usb4java.*;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.IntBuffer;


public class Main {


    public static void main(String[] args) {

//        InfoKkt infoKkt=new InfoKkt();
//        BuilderRequest  request= new BuilderRequest(infoKkt);
//
//        request.build();



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

                InfoKkt infoKkt=new InfoKkt();
                BuilderRequest  request= new BuilderRequest(infoKkt);
                ByteBuffer buffer = request.build();
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


            ByteReader  byteReader=new ByteReader(buffer);
            RVersionKkt_69 responce= (RVersionKkt_69) byteReader.getResponce();
            if(responce!=null){

                System.out.println("getStx - "+byteReader.getStx());
                System.out.println("Длина сообщения - "+byteReader.getMessageLength());
                System.out.println("Код сообщения - "+byteReader.getMessageCode());
                System.out.println("Результат выполнения команды - "+responce.resultCommand);
                System.out.println("Серийный номер ККТ - "+responce.numberKkt);
                System.out.println("Модель - "+responce.modelKkt);
                System.out.println("Производитель - "+responce.vendorKkt);
                System.out.println("Версия ПО - "+responce.VersionKkt);
                System.out.println("Номер сборки ПО - "+ responce.VersionAssembler);
            }

            System.out.println(transferred.get()+ " bytes read from device");
        }
        finally
        {
            LibUsb.freeDeviceList(list, true);
        }
        LibUsb.exit(context);


    }





}


