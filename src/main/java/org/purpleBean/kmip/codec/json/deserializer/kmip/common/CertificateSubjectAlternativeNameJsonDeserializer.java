package org.purpleBean.kmip.codec.json.deserializer.kmip.common;

import org.purpleBean.kmip.codec.json.deserializer.kmip.AbstractKmipDataTypeJsonDeserializer;
import org.purpleBean.kmip.common.CertificateSubjectAlternativeName;

public class CertificateSubjectAlternativeNameJsonDeserializer extends AbstractKmipDataTypeJsonDeserializer<CertificateSubjectAlternativeName, String> {

    public CertificateSubjectAlternativeNameJsonDeserializer() {
        super(CertificateSubjectAlternativeName.kmipTag, CertificateSubjectAlternativeName.encodingType, String.class, value -> CertificateSubjectAlternativeName.builder().value(value).build());
    }
}