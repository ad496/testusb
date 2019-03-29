package com.company;



import java.nio.ByteBuffer;

import com.company.request.*;
import org.usb4java.*;

public class Main2
{
    private static final int TIMEOUT = 5000;
    static volatile boolean exit = false;
    static final Context context = new Context();
    static final DeviceHandle handle=FactoryDeviceHandler.getDeviceHandle(context);



    private static void calculateResult(ByteBuffer  buffer){

        try {
            ByteReader  byteReader=new ByteReader(buffer);
            //RTestKkt rTestKkt= (RTestKkt) byteReader.getResponce();
           // System.out.println("протокол - "+rTestKkt.protocol);
            int d=0;
//            RVersionKkt_69 responce= (RVersionKkt_69) byteReader.getResponce();
//            if(responce!=null){
//
//                System.out.println("getStx - "+byteReader.getStx());
//                System.out.println("Длина сообщения - "+byteReader.getMessageLength());
//                System.out.println("Код сообщения - "+byteReader.getMessageCode());
//                System.out.println("Результат выполнения команды - "+responce.resultCommand);
//                System.out.println("Серийный номер ККТ - "+responce.numberKkt);
//                System.out.println("Модель - "+responce.modelKkt);
//                System.out.println("Производитель - "+responce.vendorKkt);
//                System.out.println("Версия ПО - "+responce.VersionKkt);
//                System.out.println("Номер сборки ПО - "+ responce.VersionAssembler);
//            }
        }catch (Exception e){
            System.out.println(e.getMessage());
        }finally {
            exit =true;
        }
    }





    public static void main(String[] args)
    {


        if (handle == null)
        {
            System.err.println("Test device not found.");
            System.exit(1);
        }

        EventHandlingThread thread = new EventHandlingThread();
        thread.start();

        final TransferCallback bodySent = new TransferCallback()
        {
            @Override
            public void processTransfer(Transfer transfer)
            {

                try {
                    UsbWorker.read(handle, (deviceHandle, transfer1, buffer) -> calculateResult(buffer));
                } catch (UsbException e) {
                    System.out.println(e.getMessage());
                    exit=true;
                }finally {
                   // LibUsb.freeTransfer(transfer);

                }


            }
        };

        //InfoKkt infoKkt=new InfoKkt();
        TestKkt testKkt=new TestKkt();
      //  TestPapir  testPapir=new TestPapir();
        BuilderRequest request= new BuilderRequest(testKkt);

        try {
            UsbWorker.write(handle,request.build(),bodySent);
        } catch (Exception e) {
            exit =true;
        }

        while (!exit)
        {
            Thread.yield();
        }
        try {
            LibUsb.close(handle);
        }catch (Exception e){

        }

        thread.abort();
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        LibUsb.exit(context);
        System.out.println("Program finished");
    }
}
