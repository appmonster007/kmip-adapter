package org.purpleBean.kmip.codec.json.deserializer.kmip.common;

import org.purpleBean.kmip.codec.json.deserializer.AbstractKmipJsonDeserializer;
import org.purpleBean.kmip.common.CertificateIssuerAlternativeName;

public class CertificateIssuerAlternativeNameJsonDeserializer extends AbstractKmipJsonDeserializer<CertificateIssuerAlternativeName, String> {

    public CertificateIssuerAlternativeNameJsonDeserializer() {
        super(CertificateIssuerAlternativeName.kmipTag, CertificateIssuerAlternativeName.encodingType, String.class, value -> CertificateIssuerAlternativeName.builder().value(value).build());
    }
}