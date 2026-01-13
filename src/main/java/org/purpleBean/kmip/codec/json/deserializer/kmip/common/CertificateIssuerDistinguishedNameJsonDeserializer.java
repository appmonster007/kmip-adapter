package org.purpleBean.kmip.codec.json.deserializer.kmip.common;

import org.purpleBean.kmip.codec.json.deserializer.kmip.AbstractKmipDataTypeJsonDeserializer;
import org.purpleBean.kmip.common.CertificateIssuerDistinguishedName;

public class CertificateIssuerDistinguishedNameJsonDeserializer extends AbstractKmipDataTypeJsonDeserializer<CertificateIssuerDistinguishedName, String> {

    public CertificateIssuerDistinguishedNameJsonDeserializer() {
        super(CertificateIssuerDistinguishedName.kmipTag, CertificateIssuerDistinguishedName.encodingType, String.class, value -> CertificateIssuerDistinguishedName.builder().value(value).build());
    }
}