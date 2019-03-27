package com.company.data.tag;

import com.google.common.base.Preconditions;
import com.google.common.io.LittleEndianDataInputStream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;

import static com.google.common.collect.Lists.newArrayList;

/**
 * Вспомогательный класс для чтения бинарных данных при формировании тэгов
 */
public class TagReader {
    private final static Logger LOGGER = LoggerFactory.getLogger(TagReader.class);

    private final LittleEndianDataInputStream stream;

    public TagReader(InputStream stream) {
        if (stream instanceof LittleEndianDataInputStream) {
            this.stream = (LittleEndianDataInputStream) stream;
        } else {
            this.stream = new LittleEndianDataInputStream(stream);
        }
    }

    public TagBuffer readTagBuffer() throws IOException {
        int tagId = stream.readUnsignedShort();
        int tagValueLength = stream.readUnsignedShort();

        Preconditions.checkState(tagValueLength >= 0, "Negative tag length %s", tagValueLength);

        byte[] tagValue = new byte[tagValueLength];
        if (tagValue.length != 0) {
            stream.readFully(tagValue);
        }

        return new TagBuffer(tagId, ByteBuffer.wrap(tagValue));
    }

    public List<TagBuffer> readTagBuffers() throws IOException {
        ArrayList<TagBuffer> tags = newArrayList();
        try {
            while (true) {
                tags.add(readTagBuffer());
            }
        } catch (EOFException ignored) {
        }

        return tags;
    }
}
