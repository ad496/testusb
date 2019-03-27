package com.company.data.document;

/**
 * Интерфейс фискального документа
 */
public interface FiscalDocument {

    /**
     * Получить версию протокола документа
     *
     * @return версию протокола документа
     */
    String getProtocolVersion();

    /**
     * Сохранить версию протокола документа
     *
     * @param protocolVersion версия протокола документа
     */
    void setProtocolVersion(String protocolVersion);

    /**
     * Получить идентификатор кода документа
     *
     * @return идентификатор кода документа
     */
    int getDocumentCode();

    /**
     * Сохранить идентификатор кода документа
     *
     * @param documentCode идентификатор кода документа
     */
    void setDocumentCode(int documentCode);

    /**
     * Получить бинарные данные ККТ
     *
     * @return бинарные данные ККТ
     */
    byte[] getRawData();

    /**
     * Сохранить бинарные данные ККТ
     *
     * @param rawData бинарные данные ККТ
     */
    void setRawData(byte[] rawData);


    /**
     * Получить номер фискального документа
     *
     * @return номер фискального документа
     */
    Long getFiscalDocumentNumber();

    /**
     * Получить фискальный признак документа
     *
     * @return фискальный признак документа
     */
    String getFiscalSign();

    /**
     * Получить идентификатор KKT
     *
     * @return идентификатор KKT
     */
    String getKktRegId();

    /**
     * Получить дату и время создания фискального документа
     *
     * @return дата и время создания фискального документа
     */
    Long getDateTime();

    /**
     * Получить номер фискального накопителя
     *
     * @return номер фискального накопителя
     */
    String getFiscalDriveNumber();
}
