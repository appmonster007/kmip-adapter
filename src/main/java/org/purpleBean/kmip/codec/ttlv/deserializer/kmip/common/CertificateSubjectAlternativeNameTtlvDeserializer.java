package org.purpleBean.kmip.codec.ttlv.deserializer.kmip.common;

import org.purpleBean.kmip.codec.ttlv.deserializer.AbstractKmipTtlvDeserializer;
import org.purpleBean.kmip.common.CertificateSubjectAlternativeName;

public class CertificateSubjectAlternativeNameTtlvDeserializer extends AbstractKmipTtlvDeserializer<CertificateSubjectAlternativeName, String> {

    public CertificateSubjectAlternativeNameTtlvDeserializer() {
        super(CertificateSubjectAlternativeName.kmipTag, CertificateSubjectAlternativeName.encodingType, String.class, value -> CertificateSubjectAlternativeName.builder().value(value).build());
    }
}