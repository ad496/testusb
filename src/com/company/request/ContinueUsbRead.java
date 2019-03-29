package com.company.request;

public class ContinueUsbRead extends BaseRequest
{

    public  void ContinueUsbRead(){
        this.codeMessage=(byte) 0x04;
    }
}
