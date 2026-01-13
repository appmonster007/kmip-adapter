package org.purpleBean.kmip.codec.ttlv.serializer.kmip.common;

import org.purpleBean.kmip.codec.ttlv.serializer.kmip.AbstractKmipDataTypeTtlvSerializer;
import org.purpleBean.kmip.common.CertificateSubjectAlternativeName;

public class CertificateSubjectAlternativeNameTtlvSerializer extends AbstractKmipDataTypeTtlvSerializer<CertificateSubjectAlternativeName, String> {

    public CertificateSubjectAlternativeNameTtlvSerializer() {
        super(CertificateSubjectAlternativeName::getValue);
    }
}