package com.company.data.document;


import com.company.data.tag.FiscalDocumentType;
import com.company.data.tag.annotation.DocumentType;

@DocumentType(value = FiscalDocumentType.COMMON_DOCUMENT, protocolVersion = {"1.0", "1.1"})
public class CommonFiscalDocument extends BaseFiscalDocument {
}
