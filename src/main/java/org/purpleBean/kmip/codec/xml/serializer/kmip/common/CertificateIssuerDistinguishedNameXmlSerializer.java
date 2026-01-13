package org.purpleBean.kmip.codec.xml.serializer.kmip.common;

import org.purpleBean.kmip.codec.xml.serializer.kmip.AbstractKmipDataTypeXmlSerializer;
import org.purpleBean.kmip.common.CertificateIssuerDistinguishedName;

public class CertificateIssuerDistinguishedNameXmlSerializer extends AbstractKmipDataTypeXmlSerializer<CertificateIssuerDistinguishedName, String> {

    public CertificateIssuerDistinguishedNameXmlSerializer() {
        super(CertificateIssuerDistinguishedName::getValue);
    }
}