package com.company.data.tag;

import com.google.common.base.Preconditions;
import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.ListMultimap;

import java.nio.ByteBuffer;
import java.util.List;


/**
 * Вспомогательный класс для работы с коллекцией тэгов
 */
public class Tags {
    private final ListMultimap<Integer, Tag> tags;
    private final int documentCode;

    public Tags(int documentCode, ListMultimap<Integer, Tag> tags) {
        this.documentCode = documentCode;
        this.tags = tags;
    }

    public Tags(int documentCode, List<Tag> tags) {
        this.documentCode = documentCode;

        this.tags = ArrayListMultimap.create(tags.size(), 1);
        for (Tag tag : tags) {
            this.tags.put(tag.getTagInfo().getId(), tag);
        }
    }

    public int getDocumentCode() {
        return documentCode;
    }

    public long getNumber(int tag) {
        checkOnlyOneTag(tag);
        return tags.get(tag).isEmpty() ? 0L : tags.get(tag).get(0).readNumberValue();
    }

    public List<Tag> getTags(int tag) {
        return tags.get(tag);
    }

    public String getString(int tag) {
        checkOnlyOneTag(tag);
        Object o = tags.get(tag).isEmpty() ? "" : tags.get(tag).get(0).readValue();
        return String.valueOf(o).trim();
    }

    public ByteBuffer getRaw(int tag) {
        checkOnlyOneTag(tag);
        return tags.get(tag).isEmpty() ? ByteBuffer.allocate(0) : tags.get(tag).get(0).getBinary();
    }

    private void checkOnlyOneTag(int tag) {
        Preconditions.checkState(tags.get(tag).size() < 2, "There is more than one tag '%d' in document", tag);
    }
}
