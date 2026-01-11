package org.purpleBean.kmip.codec.xml.deserializer.kmip.common;

import org.purpleBean.kmip.codec.xml.deserializer.AbstractKmipXmlDeserializer;
import org.purpleBean.kmip.common.CertificateIssuerAlternativeName;

public class CertificateIssuerAlternativeNameXmlDeserializer extends AbstractKmipXmlDeserializer<CertificateIssuerAlternativeName, String> {

    public CertificateIssuerAlternativeNameXmlDeserializer() {
        super(CertificateIssuerAlternativeName.kmipTag, CertificateIssuerAlternativeName.encodingType, String.class, value -> CertificateIssuerAlternativeName.builder().value(value).build());
    }
}