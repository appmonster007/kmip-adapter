package org.purpleBean.kmip.codec.ttlv.serializer.kmip.common;

import org.purpleBean.kmip.codec.ttlv.serializer.AbstractKmipTtlvSerializer;
import org.purpleBean.kmip.common.CertificateSubjectDistinguishedName;

public class CertificateSubjectDistinguishedNameTtlvSerializer extends AbstractKmipTtlvSerializer<CertificateSubjectDistinguishedName, String> {

    public CertificateSubjectDistinguishedNameTtlvSerializer() {
        super(CertificateSubjectDistinguishedName::getValue);
    }
}