package org.purpleBean.kmip.codec.xml.serializer.kmip.common;

import org.purpleBean.kmip.codec.xml.serializer.AbstractKmipXmlSerializer;
import org.purpleBean.kmip.common.CertificateIssuerDistinguishedName;

public class CertificateIssuerDistinguishedNameXmlSerializer extends AbstractKmipXmlSerializer<CertificateIssuerDistinguishedName, String> {

    public CertificateIssuerDistinguishedNameXmlSerializer() {
        super(CertificateIssuerDistinguishedName::getValue);
    }
}