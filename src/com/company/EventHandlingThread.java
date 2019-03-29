package com.company;

import org.usb4java.LibUsb;
import org.usb4java.LibUsbException;

/**
 * Demonstrates how to do asynchronous bulk transfers. This demo sends some
 * hardcoded data to an Android Device (Samsung Galaxy Nexus) and receives some
 * data from it.
 *
 * If you have a different Android device then you can get this demo working by
 * changing the vendor/product id, the interface number and the endpoint
 * addresses.
 *
 * In this example the event handling is done with a thread which calls the
 * {@link LibUsb#handleEventsTimeout(org.usb4java.Context, long)} method in a
 * loop. You could also run this command inside the main application loop if
 * there is one.
 *
 * @author Klaus Reimer <k@ailis.de>
 */

public class EventHandlingThread extends Thread
{

    private volatile boolean abort;

    public void abort()
    {
        this.abort = true;
    }

    @Override
    public void run()
    {
        while (!this.abort)
        {
            int result = LibUsb.handleEventsTimeout(null, 500000);
            if (result != LibUsb.SUCCESS)
                throw new LibUsbException("Unable to handle events", result);
        }
    }
}
