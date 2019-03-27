package com.company.data.tag;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import java.util.Arrays;
import java.util.Optional;

public enum FiscalDocumentTag {
    DEFAULT(-1, "", TagInType.UNKNOWN, TagOutType.STRING),
    AUTO_MODE(1001, "автоматический режим", TagInType.BYTE, TagOutType.STRING),
    OFFLINE_MODE(1002, "автономный режим", TagInType.BYTE, TagOutType.STRING),
    BANK_AGENT_ADDRESS(1003, "адрес банковского агента", TagInType.ASCII, TagOutType.STRING),
    BANK_SUBAGENT_ADDRESS(1004, "адрес банковского субагента", TagInType.ASCII, TagOutType.STRING),
    TRANSFER_PAYMENTS_OPERATOR_ADDRESS(1005, "адрес оператора по переводу денежных средств", TagInType.ASCII, TagOutType.STRING),
    BUYER_ADDRESS(1008, "адрес покупателя", TagInType.ASCII, TagOutType.STRING),
    RETAIL_PLACE_ADDRESS(1009, "адрес расчетов", TagInType.ASCII, TagOutType.STRING),
    BANK_AGENT_REMUNERATION(1010, "размер вознаграждения банковского агента (субагента)", TagInType.VLN, TagOutType.NUMBER),
    PAYMENT_AGENT_REMUNERATION(1011, "размер вознаграждения платежного агента (субагента)", TagInType.VLN, TagOutType.NUMBER),
    DATE_TIME(1012, "дата,время", TagInType.UNIX_TIME, TagOutType.NUMBER),
    KKT_FACTORY_NUMBER(1013, "заводской номер ККТ", TagInType.ASCII, TagOutType.STRING),
    MESSAGE_TYPE_STRING(1014, "значение типа строка", TagInType.ASCII, TagOutType.STRING),
    TRANSFER_PAYMENTS_OPERATOR_INN(1016, "ИНН оператора по переводу денежных средств", TagInType.ASCII, TagOutType.STRING),
    OFD_INN(1017, "ИНН ОФД", TagInType.ASCII, TagOutType.STRING),
    USER_INN(1018, "ИНН пользователя", TagInType.ASCII, TagOutType.STRING),
    TOTAL_SUM(1020, "ИТОГ", TagInType.VLN, TagOutType.NUMBER),
    OPERATOR(1021, "кассир", TagInType.ASCII, TagOutType.STRING),
    OFD_RESPONSE_CODE(1022, "код ответа ОФД", TagInType.BYTE, TagOutType.STRING),
    MERCHANDISE_QUANTITY(1023, "количество", TagInType.FVLN, TagOutType.NUMBER),
    BANK_AGENT_NAME(1024, "наименование банковского агента", TagInType.ASCII, TagOutType.STRING),
    BANK_SUBAGENT_NAME(1025, "наименование банковского субагента", TagInType.ASCII, TagOutType.STRING),
    TRANSFER_PAYMENTS_OPERATOR_NAME(1026, "наименование оператора по переводу денежных средств", TagInType.ASCII, TagOutType.STRING),
    PAYMENT_AGENT_NAME(1027, "наименование платежного агента", TagInType.ASCII, TagOutType.STRING),
    PAYMENT_SUBAGENT_NAME(1028, "наименование платежного субагента", TagInType.ASCII, TagOutType.STRING),
    MERCHANDISE_NAME(1030, "наименование товара", TagInType.ASCII, TagOutType.STRING),
    CASH_TOTAL_SUM(1031, "наличными", TagInType.VLN, TagOutType.NUMBER),
    TAX(1032, "налог", TagInType.STLV, TagOutType.NUMBER),
    TAXES(1033, "налоги", TagInType.STLV, TagOutType.TAGS),
    MARKUP_RATE(1034, "наценка (ставка)", TagInType.FVLN, TagOutType.NUMBER),
    MARKUP_SUM(1035, "наценка (сумма)", TagInType.VLN, TagOutType.NUMBER),
    MACHINE_NUMBER(1036, "номер автомата", TagInType.ASCII, TagOutType.STRING),
    KKT_REG_ID(1037, "номер ККТ", TagInType.ASCII, TagOutType.STRING),
    SHIFT_NUMBER(1038, "номер смены", TagInType.INT32LE, TagOutType.NUMBER),
    FISCAL_DOCUMENT_NUMBER(1040, "номер фискального документа", TagInType.INT32LE, TagOutType.NUMBER),
    FISCAL_DRIVE_NUMBER(1041, "заводской номер фискального накопителя", TagInType.ASCII, TagOutType.STRING),
    RECEIPT_NUMBER(1042, "номер чека", TagInType.INT32LE, TagOutType.NUMBER),
    RECEIPT_SUM(1043, "общая стоимость позиции с учетом скидок и наценок", TagInType.VLN, TagOutType.NUMBER),
    BANK_AGENT_OPERATION(1044, "операция банковского агента", TagInType.ASCII, TagOutType.STRING),
    BANK_SUBAGENT_OPERATION(1045, "операция банковского субагента", TagInType.ASCII, TagOutType.STRING),
    OFD(1046, "ОФД", TagInType.ASCII, TagOutType.STRING),
    USER_NAME(1048, "пользователь", TagInType.ASCII, TagOutType.STRING),
    POSTCODE(1049, "почтовый индекс", TagInType.ASCII, TagOutType.STRING),
    FISCAL_DRIVE_EXHAUSTION_SIGN(1050, "признак исчерпания ресурса ФН", TagInType.BYTE, TagOutType.STRING),
    FISCAL_DRIVE_REPLACE_REQUIRED_SIGN(1051, "признак необходимости срочной замены ФН", TagInType.BYTE, TagOutType.STRING),
    FISCAL_DRIVE_MEMORY_EXCEEDED_SIGN(1052, "признак переполнения памяти ФН", TagInType.BYTE, TagOutType.STRING),
    OFD_RESPONSE_TIMEOUT_SIGN(1053, "признак превышения времени ожидания ответа ОФД", TagInType.BYTE, TagOutType.STRING),
    OPERATION_TYPE(1054, "признак расчета", TagInType.BYTE, TagOutType.NUMBER),
    TAX_SYSTEM_SIGN(1055, "признак системы налогообложения", TagInType.BYTE, TagOutType.STRING),
    ENCRYPTION_SIGN(1056, "признак шифрования", TagInType.BYTE, TagOutType.STRING),
    PAYMENT_AGENT_MODE(1057, "применение платежными агентами (субагентами)", TagInType.BYTE, TagOutType.STRING),
    BANK_AGENT_MODE(1058, "применение банковскими агентами (субагентами)", TagInType.BYTE, TagOutType.STRING),
    RECEIPT_ITEMS(1059, "реквизиты товара", TagInType.STLV, TagOutType.TAGS),
    TAX_AUTHORITY_SITE(1060, "сайт налогового органа", TagInType.ASCII, TagOutType.STRING),
    OFD_SITE(1061, "сайт ОФД", TagInType.ASCII, TagOutType.STRING),
    TAXATION_TYPE(1062, "системы налогообложения", TagInType.BYTE, TagOutType.NUMBER),
    SALE_RATE(1063, "скидка (ставка)", TagInType.FVLN, TagOutType.NUMBER),
    SALE_SUM(1064, "скидка (сумма)", TagInType.VLN, TagOutType.NUMBER),
    TAX_NAME(1065, "сокращенное наименование налога", TagInType.ASCII, TagOutType.STRING),
    OFD_MESSAGES_FOR_KKT(1067, "сообщение оператора для ККТ", TagInType.STLV, TagOutType.TAGS),
    OFD_MESSAGES_FOR_FISCAL_DRIVE(1068, "сообщение оператора для ФН", TagInType.STLV, TagOutType.TAGS),
    MESSAGE_TO_OPERATOR(1069, "сообщение оператору", TagInType.STLV, TagOutType.TAGS),
    TAX_RATE(1070, "ставка налога", TagInType.FVLN, TagOutType.NUMBER),
    STORNO_ITEMS(1071, "сторно товара",  TagInType.STLV, TagOutType.TAGS),
    TAX_SUM(1072, "сумма налога", TagInType.VLN, TagOutType.NUMBER),
    BANK_AGENT_PHONE(1073, "телефон банковского агента", TagInType.ASCII, TagOutType.STRING),
    PAYMENT_AGENT_PHONE(1074, "телефон платежного агента", TagInType.ASCII, TagOutType.STRING),
    TRANSFER_PAYMENTS_OPERATOR_PHONE(1075, "телефона оператора по переводу денежных средств", TagInType.ASCII, TagOutType.STRING),
    FISCAL_SIGN(1077, "фискальный признак документа", TagInType.BYTE_ARRAY, TagOutType.RAW),
    OFD_FISCAL_SIGN(1078, "фискальный признак оператора", TagInType.BYTE_ARRAY, TagOutType.RAW),
    MERCHANDISE_PRICE(1079, "цена за единицу", TagInType.VLN, TagOutType.NUMBER),
    MERCHANDISE_BARCODE(1080, "штриховой код EAN13", TagInType.ASCII, TagOutType.STRING),
    ECASH_TOTAL_SUM(1081, "электронными", TagInType.VLN, TagOutType.NUMBER),
    BANK_SUBAGENT_PHONE(1082, "телефон банковского субагента", TagInType.ASCII, TagOutType.STRING),
    PAYMENT_SUBAGENT_PHONE(1083, "телефон платежного субагента", TagInType.ASCII, TagOutType.STRING),
    EXTRA_DATA(1084, "дополнительный реквизит", TagInType.STLV, TagOutType.TAGS),
    NOT_SENT_DOCUMENT_NUMBER(1097, "количество непереданных документов ФД", TagInType.INT32LE, TagOutType.NUMBER),
    FIRST_NOT_SENT_DOCUMENT_DATE_TIME(1098, "дата и время первого из непереданных ФД", TagInType.UNIX_TIME, TagOutType.NUMBER),
    CORRECTION_REASON_CODE(1101, "код причины перерегистрации", TagInType.BYTE, TagOutType.STRING),
    NDS_18_SUM(1102, "НДС итога чека со ставкой 18% значение реквизита - Сумма рассчитанного налога", TagInType.VLN, TagOutType.NUMBER),
    NDS_10_SUM(1103, "НДС итога чека со ставкой 10% сумма рассчитанного налога", TagInType.VLN, TagOutType.NUMBER),
    NDS_0_SUM(1104, "НДС итога чека со ставкой 0% сумма (часть итога) к которой применяется ставка 0", TagInType.VLN, TagOutType.NUMBER),
    NDS_NONE_SUM(1105, "НДС не облагается сумма (часть итога) к которой не применяется НДС", TagInType.VLN, TagOutType.NUMBER),
    NDS_18_CALCULATED_SUM(1106, "НДС итога чека с рассчитанной ставкой 18% значение реквизита - Сумма рассчитанного налога", TagInType.VLN, TagOutType.NUMBER),
    NDS_10_CALCULATED_SUM(1107, "НДС итога чека с рассчитанной ставкой 10% значение реквизита - Сумма рассчитанного налога", TagInType.VLN, TagOutType.NUMBER),
    INTERNET_PAYMENTS_SIGN(1108, "признак расчетов в сети Интернет", TagInType.BYTE, TagOutType.STRING),
    SERVICE_SIGN(1109, "признак работы в сфере услуг", TagInType.BYTE, TagOutType.STRING),
    BSO_SIGN(1110, "применяется для формирования БСО", TagInType.BYTE, TagOutType.STRING),
    FISCAL_DOCS_BY_SHIFT_QUANTITY(1111, "количество фискальных документов за смену", TagInType.INT32LE, TagOutType.NUMBER),
    SALES_OR_MARKUPS(1112, "cкидки/наценки", TagInType.STLV, TagOutType.TAGS),
    SALE_NAME(1113, "наименование скидки", TagInType.ASCII, TagOutType.STRING),
    MARKUP_NAME(1114, "наименование наценки", TagInType.ASCII, TagOutType.STRING),

    ADDRESS_TO_CHECK_FISCAL_SIGN(1115, "адрес сайта для проверки ФП", TagInType.ASCII, TagOutType.STRING),
    FIRST_NOT_SENT_DOCUMENT_INDEX(1116, "номер первого непереданного документа", TagInType.INT32LE, TagOutType.STRING),
    SENDER_ADDRESS(1117, "адрес отправителя", TagInType.ASCII, TagOutType.STRING),
    RECEIPTS_BY_SHIFT_QUANTITY(1118, "количество кассовых чеков за смену", TagInType.INT32LE, TagOutType.NUMBER),
    RECEIVE_PAYMENTS_OPERATOR_PHONE(1119, "телефон оператора по приему платежей", TagInType.ASCII, TagOutType.STRING);


    //1006, "адрес платежного агента", "ASCII"
    //1007, "адрес платежного субагента", "ASCII"
    //1015, "значение типа целое", "INT32LE"
    //1019, "информационное cообщение", "ASCII"
    //1029, "наименование реквизита", "ASCII"
    //1039, "зарезервирован", "ASCII"
    //1047, "параметр настройки", "STLV"
    //1066, "сообщение", "ASCII"
    //1076, "тип сообщения", "ASCII"
    //1085, "наименование дополнительного реквизита", "ASCII"
    //1086, "значение дополнительного реквизита", "ASCII"
    //1087, "Итог смены", "ASCII"
    //1088, "приход наличными", "FVLN"
    //1089, "приход электронными", "FVLN"
    //1090,"возврат прихода наличными", "FVLN"
    //1091, "возврат прихода электронными", "FVLN"
    //1092,"расход наличными", "FVLN"
    //1093, "расход электронными", "FVLN"
    //1094, "возврат расхода наличными", "FVLN"
    //1095, "возврат расхода электронными", "FVLN"
    //1096, "номер корректируемого фискального документа", "INT32LE"
    //1099, "сводный итог", "FVLN"
    //1100, "номер предписания", "INT32LE"


    private final int id;
    private final String label;
    private final TagInType inType;
    private final TagOutType outType;


    FiscalDocumentTag(int id, String label, TagInType inType, TagOutType outType) {
        this.id = id;
        this.label = label;
        this.inType = inType;
        this.outType = outType;
    }

    @JsonCreator
    public static FiscalDocumentTag forValue(int id) {
        Optional<FiscalDocumentTag> status = Arrays.asList(FiscalDocumentTag.values())
                .stream()
                .filter(s -> s.id == id)
                .findFirst();

        return status.orElseThrow(() -> new IllegalArgumentException("Unknown FiscalDocumentTag code"));
    }

    @JsonValue
    public int getId() {
        return id;
    }

    public String getLabel() {
        return label;
    }

    public TagInType getInType() {
        return inType;
    }

    public TagOutType getOutType() {
        return outType;
    }
}