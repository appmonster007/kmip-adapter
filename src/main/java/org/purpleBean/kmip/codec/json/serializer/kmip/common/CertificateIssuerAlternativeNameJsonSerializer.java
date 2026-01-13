package org.purpleBean.kmip.codec.json.serializer.kmip.common;

import org.purpleBean.kmip.codec.json.serializer.kmip.AbstractKmipDataTypeJsonSerializer;
import org.purpleBean.kmip.common.CertificateIssuerAlternativeName;

public class CertificateIssuerAlternativeNameJsonSerializer extends AbstractKmipDataTypeJsonSerializer<CertificateIssuerAlternativeName, String> {

    public CertificateIssuerAlternativeNameJsonSerializer() {
        super(CertificateIssuerAlternativeName::getValue);
    }
}