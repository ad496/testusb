package com.company;

import com.company.request.RTestKkt;
import com.company.request.RVersionKkt_69;

public class FactoryResponveKkm {

    public static  Object getObject(byte[] bytes,int code){
        switch (code){
            case 69:{
                return new RVersionKkt_69(bytes);
            }
            case 48:{

                return new RTestKkt(bytes);
                //0,-128,14,0,14

            }
        }
        return null;
    }
}
