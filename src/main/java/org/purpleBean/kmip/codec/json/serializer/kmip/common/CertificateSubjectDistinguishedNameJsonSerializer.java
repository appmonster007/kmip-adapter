package org.purpleBean.kmip.codec.json.serializer.kmip.common;

import org.purpleBean.kmip.codec.json.serializer.AbstractKmipJsonSerializer;
import org.purpleBean.kmip.common.CertificateSubjectDistinguishedName;

public class CertificateSubjectDistinguishedNameJsonSerializer extends AbstractKmipJsonSerializer<CertificateSubjectDistinguishedName, String> {

    public CertificateSubjectDistinguishedNameJsonSerializer() {
        super(CertificateSubjectDistinguishedName::getValue);
    }
}