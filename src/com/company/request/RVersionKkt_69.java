package com.company.request;

import com.company.BaseRespoce;
import com.company.Utils;

import java.util.Arrays;

public class RVersionKkt_69 extends BaseRespoce {

// 4  Текущий статус ККТ          Uint16,LE    2
// 5  Результат выполнениякоманды Uint16,LE    2
// 6  Текущий статус принтера     Byte         1
// 7  Серийный номер ККТ          ASCII        20
// 8  Модель                      ASCII        30
// 9  Производитель               ASCII        30
// 10 Версия ПО                   Uint16,LE    2
// 11 Номер сборки ПО             Uint16,LE    2
//
    public int currentStatusKkt;
    public int resultCommand;
    public String numberKkt;
    public String modelKkt;
    public String vendorKkt;
    public int VersionKkt;
    public int VersionAssembler;

    //Ответ на команду «Информация о версии ПО ККТ»
    public RVersionKkt_69(byte[]  bytes){
        super(bytes);

        currentStatusKkt= Utils.unsignedIntToInt(bytes[0],bytes[1]);
        resultCommand=Utils.unsignedIntToInt(bytes[2],bytes[3]);

        numberKkt=Utils.getStringASCII(Arrays.copyOfRange(bytes, 5, 24));
        modelKkt=Utils.getStringASCII(Arrays.copyOfRange(bytes, 24, 53));
        vendorKkt=Utils.getStringASCII(Arrays.copyOfRange(bytes, 54, 83));
        VersionKkt=Utils.unsignedIntToInt(bytes[84],bytes[85]);
        VersionAssembler=Utils.unsignedIntToInt(bytes[86],bytes[87]);




    }
}
