package org.purpleBean.kmip.codec.xml.deserializer.kmip.common;

import org.purpleBean.kmip.codec.xml.deserializer.AbstractKmipXmlDeserializer;
import org.purpleBean.kmip.common.CertificateIssuerDistinguishedName;

public class CertificateIssuerDistinguishedNameXmlDeserializer extends AbstractKmipXmlDeserializer<CertificateIssuerDistinguishedName, String> {

    public CertificateIssuerDistinguishedNameXmlDeserializer() {
        super(CertificateIssuerDistinguishedName.kmipTag, CertificateIssuerDistinguishedName.encodingType, String.class, value -> CertificateIssuerDistinguishedName.builder().value(value).build());
    }
}