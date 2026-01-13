package org.purpleBean.kmip.codec.ttlv.serializer.kmip.common;

import org.purpleBean.kmip.codec.ttlv.serializer.kmip.AbstractKmipDataTypeTtlvSerializer;
import org.purpleBean.kmip.common.CertificateIssuerAlternativeName;

public class CertificateIssuerAlternativeNameTtlvSerializer extends AbstractKmipDataTypeTtlvSerializer<CertificateIssuerAlternativeName, String> {

    public CertificateIssuerAlternativeNameTtlvSerializer() {
        super(CertificateIssuerAlternativeName::getValue);
    }
}