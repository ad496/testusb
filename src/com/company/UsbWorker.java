package com.company;

import org.usb4java.*;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

public class UsbWorker {

    private static final int TIMEOUT = 10000;
    private static final int SIZE_OUT = 100000;

    public static void write(DeviceHandle handle, final ByteBuffer buffer,TransferCallback callback) throws Exception {
        Transfer transfer = LibUsb.allocTransfer();
        LibUsb.fillBulkTransfer(transfer, handle, (byte) 0x02, buffer,   callback, null, TIMEOUT);
        int result = LibUsb.submitTransfer(transfer);
        if (result != LibUsb.SUCCESS)
        {
            throw new UsbException(new LibUsbException("Unable to submit transfer", result).getMessage());
        }
    }

    public static synchronized void read(final DeviceHandle handle,IUsbAction<DeviceHandle,Transfer,ByteBuffer> action) throws UsbException {
        final ByteBuffer buffer = BufferUtils.allocateByteBuffer(SIZE_OUT).order( ByteOrder.LITTLE_ENDIAN);
        Transfer transfer = LibUsb.allocTransfer();
        LibUsb.fillBulkTransfer(transfer, handle, (byte) 0x81, buffer, new TransferCallback() {
            @Override
            public void processTransfer(Transfer transfer1) {

                if(action!=null){
                    action.action(handle,transfer1,buffer);
                }
            }
        }, null, TIMEOUT);
        int result = LibUsb.submitTransfer(transfer);
        if (result != LibUsb.SUCCESS)
        {
            throw new UsbException(new LibUsbException("Unable to submit transfer", result).getMessage());
        }
    }


}
