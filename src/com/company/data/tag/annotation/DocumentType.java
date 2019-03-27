package com.company.data.tag.annotation;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Repeatable(DocumentTypes.class)
public @interface DocumentType {
    int value();

    String[] protocolVersion();
}

