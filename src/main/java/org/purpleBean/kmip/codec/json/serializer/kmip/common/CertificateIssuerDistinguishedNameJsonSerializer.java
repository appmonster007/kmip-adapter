package org.purpleBean.kmip.codec.json.serializer.kmip.common;

import org.purpleBean.kmip.codec.json.serializer.kmip.AbstractKmipDataTypeJsonSerializer;
import org.purpleBean.kmip.common.CertificateIssuerDistinguishedName;

public class CertificateIssuerDistinguishedNameJsonSerializer extends AbstractKmipDataTypeJsonSerializer<CertificateIssuerDistinguishedName, String> {

    public CertificateIssuerDistinguishedNameJsonSerializer() {
        super(CertificateIssuerDistinguishedName::getValue);
    }
}