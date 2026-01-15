package org.purpleBean.kmip.codec.xml.deserializer.model.core.type;

import org.purpleBean.kmip.codec.xml.deserializer.kmip.AbstractKmipDataTypeXmlDeserializer;
import org.purpleBean.kmip.model.core.type.CertificateIssuerDistinguishedName;

public class CertificateIssuerDistinguishedNameXmlDeserializer extends AbstractKmipDataTypeXmlDeserializer<CertificateIssuerDistinguishedName, String> {

    public CertificateIssuerDistinguishedNameXmlDeserializer() {
        super(CertificateIssuerDistinguishedName.kmipTag, CertificateIssuerDistinguishedName.encodingType, String.class, value -> CertificateIssuerDistinguishedName.builder().value(value).build());
    }
}