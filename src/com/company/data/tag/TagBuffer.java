package com.company.data.tag;

import java.nio.ByteBuffer;


/**
 * Вспомогательный класс для хранения бинарного представления тэга
 */
public class TagBuffer {
    final private int tagId;
    final private ByteBuffer tagValue;

    public TagBuffer(int tagId, ByteBuffer tagValue) {
        this.tagId = tagId;
        this.tagValue = tagValue;
    }

    public int getTagId() {
        return tagId;
    }

    public ByteBuffer getTagValue() {
        return tagValue;
    }
}
