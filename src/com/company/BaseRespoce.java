package com.company;

public class BaseRespoce {

    public BaseRespoce(byte[] bytes){
        refreshStatusKkt(bytes[0],bytes[1]);
        refreshStatusPrinter(bytes[4]);
    }

    /**
     * Ширина ленты  0-80 mm   1-57mm
     */
    public int tapeWidth;
    /**
     * Протокол  0- с поддтверждением 1 без поддтверждения
     */
    public int protocol;
    /**
     * Тестирование оборудования 0 - вкл 1 - выкл
     */
    public boolean isTestMode;


    // 1   Ширина ленты              0-80 mm              1-57mm
    // 7   Протокол                  0- с поддтверждением 1 без поддтверждения
    // 15  Тестирование оборудования 0 - вкл              1 - выкл

    /**
     * Готовность дисплея покупателя
     */
    public boolean printerDisplayStatys;

    /**
     * Состояние датчика денежного  ящика
     */
    public boolean printerBoxMoneyStatus;
    /**
     * Режим работы 0-Технологический 1- Основной
     */
    public boolean printerWorkStatus;
    /**
     * Ошибка принтера 0-Ошибка 1-нет
     */
    public boolean printerIsError;
    /**
     * Отрезной нож  0- Включен 1- Выключен
     */
    public boolean printerCuttingKnifeStatus;
    /**
     * Конец бумаги принтера  0-Нет 1-Да
     */
    public boolean printerIsEndPaper;



    protected void refreshStatusKkt(byte b1, byte b2){

        if(Utils.getBitPos(b1,1)==0){
            tapeWidth=80;
        }else {
            tapeWidth=57;
        }

        if(Utils.getBitPos(b1,7)==0){
            protocol=1;
        }else {
            protocol=2;
        }

        if(Utils.getBitPos(b2,8)==0){
            isTestMode=true;
        }else {
            isTestMode=false;
        }
    }
    //0 Готовность дисплея покупателя         0-нет             1- да
    //1 Состояние датчика денежного  ящика    0-Замкнут         1-Разомкнут
    //2 Режим работы                          0-Технологический 1- Основной
    //3 Ошибка принтера                       0-Ошибка          1-нет
    //4 Отрезной нож                          0- Включен        1- Выключен
    //5 Конец бумаги принтера                 0-Нет             1-Да
    //6..7 Не используется
    //00001110

    //public String f="";
    protected void refreshStatusPrinter(byte b){
      //  f="Текущий статус ghbsdsd "+String.format("%8s", Integer.toBinaryString(b & 0xFF)).replace(' ', '0');
        if(Utils.getBitPos(b,0)==0){
            printerDisplayStatys=false;
        }else {
            printerDisplayStatys=true;
        }

        if(Utils.getBitPos(b,1)==0){
            printerBoxMoneyStatus=true;
        }else {
            printerBoxMoneyStatus=false;
        }

        if(Utils.getBitPos(b,2)==0){
            printerWorkStatus=false;
        }else {
            printerWorkStatus=false;
        }

        if(Utils.getBitPos(b,3)==0){
            printerIsError=true;
        }else {
            printerIsError=false;
        }

        if(Utils.getBitPos(b,4)==0){
            printerCuttingKnifeStatus=true;
        }else {
            printerCuttingKnifeStatus=false;
        }

        if(Utils.getBitPos(b,5)==0){
            printerIsEndPaper=false;
        }else {
            printerIsEndPaper=true;
        }
    }

}
