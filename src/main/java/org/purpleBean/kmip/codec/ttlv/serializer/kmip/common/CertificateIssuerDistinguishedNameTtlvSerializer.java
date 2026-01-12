package org.purpleBean.kmip.codec.ttlv.serializer.kmip.common;

import org.purpleBean.kmip.codec.ttlv.serializer.AbstractKmipTtlvSerializer;
import org.purpleBean.kmip.common.CertificateIssuerDistinguishedName;

public class CertificateIssuerDistinguishedNameTtlvSerializer extends AbstractKmipTtlvSerializer<CertificateIssuerDistinguishedName, String> {

    public CertificateIssuerDistinguishedNameTtlvSerializer() {
        super(CertificateIssuerDistinguishedName::getValue);
    }
}