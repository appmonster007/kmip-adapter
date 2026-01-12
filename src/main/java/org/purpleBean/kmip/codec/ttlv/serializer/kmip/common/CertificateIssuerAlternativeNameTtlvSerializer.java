package org.purpleBean.kmip.codec.ttlv.serializer.kmip.common;

import org.purpleBean.kmip.codec.ttlv.serializer.AbstractKmipTtlvSerializer;
import org.purpleBean.kmip.common.CertificateIssuerAlternativeName;

public class CertificateIssuerAlternativeNameTtlvSerializer extends AbstractKmipTtlvSerializer<CertificateIssuerAlternativeName, String> {

    public CertificateIssuerAlternativeNameTtlvSerializer() {
        super(CertificateIssuerAlternativeName::getValue);
    }
}