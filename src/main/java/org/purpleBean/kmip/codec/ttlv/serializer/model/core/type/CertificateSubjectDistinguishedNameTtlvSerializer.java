package org.purpleBean.kmip.codec.ttlv.serializer.model.core.type;

import org.purpleBean.kmip.codec.ttlv.serializer.api.AbstractKmipDataTypeTtlvSerializer;
import org.purpleBean.kmip.model.core.type.CertificateSubjectDistinguishedName;

public class CertificateSubjectDistinguishedNameTtlvSerializer extends AbstractKmipDataTypeTtlvSerializer<CertificateSubjectDistinguishedName, String> {

    public CertificateSubjectDistinguishedNameTtlvSerializer() {
        super(CertificateSubjectDistinguishedName::getValue);
    }
}