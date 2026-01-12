package org.purpleBean.kmip.codec.ttlv.deserializer.kmip.common;

import org.purpleBean.kmip.codec.ttlv.deserializer.AbstractKmipTtlvDeserializer;
import org.purpleBean.kmip.common.CertificateIssuerDistinguishedName;

public class CertificateIssuerDistinguishedNameTtlvDeserializer extends AbstractKmipTtlvDeserializer<CertificateIssuerDistinguishedName, String> {

    public CertificateIssuerDistinguishedNameTtlvDeserializer() {
        super(CertificateIssuerDistinguishedName.kmipTag, CertificateIssuerDistinguishedName.encodingType, String.class, value -> CertificateIssuerDistinguishedName.builder().value(value).build());
    }
}