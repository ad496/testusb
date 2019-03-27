package com.company.data.session;


import java.io.Serializable;

/**
 * Заголовок внутреннего пакета для хранения сессионных  данных (мета данных и данных ККТ)
 */
public class SessionHeader implements Serializable {
    private static final long serialVersionUID = 43L;

    /**
     * Подпись пакета данных ККТ
     */
    private final byte[] signature;

    /**
     * Версия s протокола
     */
    private final byte[] sProtocolVersion;

    /**
     * Версия а протокола
     */
    private final byte[] aProtocolVersion;

    /**
     * Фискальный номер документа
     */
    private final byte[] fnNumber;

    /**
     * Создание заголовка пакета сессионных данных c параметрами
     *
     * @param signature        подпись пакета данных ККТ
     * @param sProtocolVersion версия s протокола
     * @param aProtocolVersion версия а протокола
     * @param fnNumber         фискальный номер документа
     */
    public SessionHeader(byte[] signature, byte[] sProtocolVersion, byte[] aProtocolVersion, byte[] fnNumber) {
        this.signature = signature;
        this.sProtocolVersion = sProtocolVersion;
        this.aProtocolVersion = aProtocolVersion;
        this.fnNumber = fnNumber;
    }

    /**
     * Получить подпись пакета данных ККТ
     *
     * @return массив байт подписи пакета данных ККТ
     */
    public byte[] getSignature() {
        return signature;
    }

    /**
     * Получить версию s протокола
     *
     * @return массив байт версии s протокола
     */
    public byte[] getSProtocolVersion() {
        return sProtocolVersion;
    }

    /**
     * Получить версию а протокола
     *
     * @return массив байт версии a протокола
     */
    public byte[] getAProtocolVersion() {
        return aProtocolVersion;
    }

    /**
     * Получить фискальный номер документа
     *
     * @return массив байт фискального номера документа
     */
    public byte[] getFnNumber() {
        return fnNumber;
    }
}
