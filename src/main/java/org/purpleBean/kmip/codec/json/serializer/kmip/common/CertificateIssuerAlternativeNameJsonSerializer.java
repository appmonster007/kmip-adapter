package org.purpleBean.kmip.codec.json.serializer.kmip.common;

import org.purpleBean.kmip.codec.json.serializer.AbstractKmipJsonSerializer;
import org.purpleBean.kmip.common.CertificateIssuerAlternativeName;

public class CertificateIssuerAlternativeNameJsonSerializer extends AbstractKmipJsonSerializer<CertificateIssuerAlternativeName, String> {

    public CertificateIssuerAlternativeNameJsonSerializer() {
        super(CertificateIssuerAlternativeName::getValue);
    }
}