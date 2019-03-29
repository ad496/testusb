package com.company.request;

import com.company.data.support.Int16LE;

public class TestPapir extends BaseRequest {
    public TestPapir(){
        this.codeMessage=(byte) 0x52;
        byte[]bytes=new byte[]{0x01,0x1};
        this.messageData=bytes;
    }
}
