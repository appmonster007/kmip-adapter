package org.purpleBean.kmip.codec.ttlv.serializer.model.core.type;

import org.purpleBean.kmip.codec.ttlv.serializer.kmip.AbstractKmipDataTypeTtlvSerializer;
import org.purpleBean.kmip.model.core.type.CertificateIssuerDistinguishedName;

public class CertificateIssuerDistinguishedNameTtlvSerializer extends AbstractKmipDataTypeTtlvSerializer<CertificateIssuerDistinguishedName, String> {

    public CertificateIssuerDistinguishedNameTtlvSerializer() {
        super(CertificateIssuerDistinguishedName::getValue);
    }
}