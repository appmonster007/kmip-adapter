package org.purpleBean.kmip.codec.ttlv.deserializer.model.core.type;

import org.purpleBean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purpleBean.kmip.model.core.type.CertificateIssuerDistinguishedName;

public class CertificateIssuerDistinguishedNameTtlvDeserializer extends AbstractKmipDataTypeTtlvDeserializer<CertificateIssuerDistinguishedName, String> {

    public CertificateIssuerDistinguishedNameTtlvDeserializer() {
        super(CertificateIssuerDistinguishedName.kmipTag, CertificateIssuerDistinguishedName.encodingType, String.class, value -> CertificateIssuerDistinguishedName.builder().value(value).build());
    }
}