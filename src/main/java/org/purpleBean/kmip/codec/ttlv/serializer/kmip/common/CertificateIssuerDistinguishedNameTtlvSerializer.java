package org.purpleBean.kmip.codec.ttlv.serializer.kmip.common;

import org.purpleBean.kmip.codec.ttlv.serializer.kmip.AbstractKmipDataTypeTtlvSerializer;
import org.purpleBean.kmip.common.CertificateIssuerDistinguishedName;

public class CertificateIssuerDistinguishedNameTtlvSerializer extends AbstractKmipDataTypeTtlvSerializer<CertificateIssuerDistinguishedName, String> {

    public CertificateIssuerDistinguishedNameTtlvSerializer() {
        super(CertificateIssuerDistinguishedName::getValue);
    }
}