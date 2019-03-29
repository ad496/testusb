package com.company;

import org.usb4java.*;

public class FactoryDeviceHandler {

  public static   DeviceHandle getDeviceHandle(Context context){
        int result = LibUsb.init(context);
        if (result < 0)
        {
            throw new LibUsbException("Unable to initialize libusb", result);
        }
        DeviceHandle handle = new DeviceHandle();
        final DeviceList list = new DeviceList();
        result = LibUsb.getDeviceList(context, list);
        if (result < 0)
        {
            throw new LibUsbException("Unable to get device list", result);
        }
        for (Device device: list)
        {
            DeviceDescriptor descriptor = new DeviceDescriptor();
            result = LibUsb.getDeviceDescriptor(device, descriptor);
            if (result != LibUsb.SUCCESS) throw new LibUsbException("Unable to read device descriptor", result);
            if(descriptor.idVendor()==1155){
                result = LibUsb.open(device, handle);
                if (result != LibUsb.SUCCESS) {
                    throw new LibUsbException("Unable to open USB device", result);
                }
            }

            System.out.println(descriptor.idVendor()+" "+ descriptor.idProduct());
        }
        return handle;

    }
}
