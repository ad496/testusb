package com.company.data.session;


import java.io.Serializable;

/**
 * Внутренний пакет для хранения сессионных данных (мета данных и данных ККТ)
 */
public class SessionPackage implements Serializable {
    private static final long serialVersionUID = 43L;

    /**
     * Идентификатор DataServer, который получил данные KKT
     */
    private final String dataServerId;

    /**
     * Идентификатор соединения с KKT
     */
    private final String clientId;

    /**
     * Заголовок сессионного пакета
     */
    private final SessionHeader header;

    /**
     * Контейнер пакета бинарных данных ККТ
     */
    private final byte[] container;


    /**
     * Создание пакета сессионных данных c параметрами
     *
     * @param clientId     идентификатор соединения с KKT
     * @param dataServerId идентификатор Data-Server, который получил данные KKT
     * @param header       заголовок SessionPackage
     * @param container    контейнер пакета бинарных данных ККТ
     */
    public SessionPackage(String clientId, String dataServerId, SessionHeader header, byte[] container) {
        this.clientId = clientId;
        this.dataServerId = dataServerId;
        this.header = header;
        this.container = container;
    }

    /**
     * Получить идентификатор DataServer, который получил данные KKT
     *
     * @return идентификатор DataServer, который получил данные KKT
     */
    public String getDataServerId() {
        return dataServerId;
    }

    /**
     * Получить идентификатор соединения с KKT
     *
     * @return идентификатор соединения с KKT
     */
    public String getClientId() {
        return clientId;
    }

    /**
     * Получить заголовок сессионного пакета
     *
     * @return заголовок сессионного пакета
     */
    public SessionHeader getHeader() {
        return header;
    }

    /**
     * Получить контейнер пакета бинарных данных ККТ
     *
     * @return контейнер пакета бинарных данных ККТ
     */
    public byte[] getContainer() {
        return container;
    }
}
