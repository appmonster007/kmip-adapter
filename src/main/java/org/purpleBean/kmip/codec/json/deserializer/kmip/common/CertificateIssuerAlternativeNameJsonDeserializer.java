package org.purpleBean.kmip.codec.json.deserializer.kmip.common;

import org.purpleBean.kmip.codec.json.deserializer.kmip.AbstractKmipDataTypeJsonDeserializer;
import org.purpleBean.kmip.common.CertificateIssuerAlternativeName;

public class CertificateIssuerAlternativeNameJsonDeserializer extends AbstractKmipDataTypeJsonDeserializer<CertificateIssuerAlternativeName, String> {

    public CertificateIssuerAlternativeNameJsonDeserializer() {
        super(CertificateIssuerAlternativeName.kmipTag, CertificateIssuerAlternativeName.encodingType, String.class, value -> CertificateIssuerAlternativeName.builder().value(value).build());
    }
}