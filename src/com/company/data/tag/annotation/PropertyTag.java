package com.company.data.tag.annotation;



import com.company.data.tag.FiscalDocumentTag;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD, ElementType.FIELD})
@Repeatable(PropertyTags.class)
public @interface PropertyTag {
    FiscalDocumentTag value();

    FiscalDocumentTag listItemTag() default FiscalDocumentTag.DEFAULT;

    String[] protocolVersion();
}
