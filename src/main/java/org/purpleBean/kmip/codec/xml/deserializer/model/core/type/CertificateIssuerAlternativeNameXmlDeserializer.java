package org.purpleBean.kmip.codec.xml.deserializer.model.core.type;

import org.purpleBean.kmip.codec.xml.deserializer.kmip.AbstractKmipDataTypeXmlDeserializer;
import org.purpleBean.kmip.model.core.type.CertificateIssuerAlternativeName;

public class CertificateIssuerAlternativeNameXmlDeserializer extends AbstractKmipDataTypeXmlDeserializer<CertificateIssuerAlternativeName, String> {

    public CertificateIssuerAlternativeNameXmlDeserializer() {
        super(CertificateIssuerAlternativeName.kmipTag, CertificateIssuerAlternativeName.encodingType, String.class, value -> CertificateIssuerAlternativeName.builder().value(value).build());
    }
}