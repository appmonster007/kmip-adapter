package org.purpleBean.kmip.codec.ttlv.deserializer.kmip.common;

import org.purpleBean.kmip.codec.ttlv.deserializer.kmip.AbstractKmipDataTypeTtlvDeserializer;
import org.purpleBean.kmip.common.CertificateIssuerDistinguishedName;

public class CertificateIssuerDistinguishedNameTtlvDeserializer extends AbstractKmipDataTypeTtlvDeserializer<CertificateIssuerDistinguishedName, String> {

    public CertificateIssuerDistinguishedNameTtlvDeserializer() {
        super(CertificateIssuerDistinguishedName.kmipTag, CertificateIssuerDistinguishedName.encodingType, String.class, value -> CertificateIssuerDistinguishedName.builder().value(value).build());
    }
}