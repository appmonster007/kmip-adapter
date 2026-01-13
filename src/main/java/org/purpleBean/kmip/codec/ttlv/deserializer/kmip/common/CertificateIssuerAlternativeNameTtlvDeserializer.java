package org.purpleBean.kmip.codec.ttlv.deserializer.kmip.common;

import org.purpleBean.kmip.codec.ttlv.deserializer.kmip.AbstractKmipDataTypeTtlvDeserializer;
import org.purpleBean.kmip.common.CertificateIssuerAlternativeName;

public class CertificateIssuerAlternativeNameTtlvDeserializer extends AbstractKmipDataTypeTtlvDeserializer<CertificateIssuerAlternativeName, String> {

    public CertificateIssuerAlternativeNameTtlvDeserializer() {
        super(CertificateIssuerAlternativeName.kmipTag, CertificateIssuerAlternativeName.encodingType, String.class, value -> CertificateIssuerAlternativeName.builder().value(value).build());
    }
}