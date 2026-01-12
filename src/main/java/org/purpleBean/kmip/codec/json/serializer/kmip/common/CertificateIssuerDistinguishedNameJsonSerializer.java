package org.purpleBean.kmip.codec.json.serializer.kmip.common;

import org.purpleBean.kmip.codec.json.serializer.AbstractKmipJsonSerializer;
import org.purpleBean.kmip.common.CertificateIssuerDistinguishedName;

public class CertificateIssuerDistinguishedNameJsonSerializer extends AbstractKmipJsonSerializer<CertificateIssuerDistinguishedName, String> {

    public CertificateIssuerDistinguishedNameJsonSerializer() {
        super(CertificateIssuerDistinguishedName::getValue);
    }
}