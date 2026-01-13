package org.purpleBean.kmip.codec.ttlv.serializer.kmip.common;

import org.purpleBean.kmip.codec.ttlv.serializer.kmip.AbstractKmipDataTypeTtlvSerializer;
import org.purpleBean.kmip.common.CertificateSubjectDistinguishedName;

public class CertificateSubjectDistinguishedNameTtlvSerializer extends AbstractKmipDataTypeTtlvSerializer<CertificateSubjectDistinguishedName, String> {

    public CertificateSubjectDistinguishedNameTtlvSerializer() {
        super(CertificateSubjectDistinguishedName::getValue);
    }
}