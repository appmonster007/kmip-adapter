package org.purpleBean.kmip.codec.ttlv.serializer.model.core.type;

import org.purpleBean.kmip.codec.ttlv.serializer.kmip.AbstractKmipDataTypeTtlvSerializer;
import org.purpleBean.kmip.model.core.type.CertificateIssuerAlternativeName;

public class CertificateIssuerAlternativeNameTtlvSerializer extends AbstractKmipDataTypeTtlvSerializer<CertificateIssuerAlternativeName, String> {

    public CertificateIssuerAlternativeNameTtlvSerializer() {
        super(CertificateIssuerAlternativeName::getValue);
    }
}