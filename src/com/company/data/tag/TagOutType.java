package com.company.data.tag;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import java.util.Arrays;
import java.util.Optional;

public enum TagOutType {
    STRING(1),
    NUMBER(2),
    TAGS(3),
    RAW(4);

    private final int code;

    TagOutType(int code) {
        this.code = code;
    }

    @JsonCreator
    public static TagOutType forValue(int code) {
        Optional<TagOutType> status = Arrays.asList(TagOutType.values())
                .stream()
                .filter(s -> s.code == code)
                .findFirst();

        return status.orElseThrow(() -> new IllegalArgumentException("Unknown FiscalDocumentTagName code"));
    }

    @JsonValue
    public int getCode() {
        return code;
    }
}