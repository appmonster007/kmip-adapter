package org.purpleBean.kmip.codec.xml.serializer.kmip.common;

import org.purpleBean.kmip.common.CertificateIssuerAlternativeName;
import org.purpleBean.kmip.codec.xml.serializer.AbstractKmipXmlSerializer;

public class CertificateIssuerAlternativeNameXmlSerializer extends AbstractKmipXmlSerializer<CertificateIssuerAlternativeName, String> {

    public CertificateIssuerAlternativeNameXmlSerializer() {
        super(CertificateIssuerAlternativeName::getValue);
    }
}