package org.purpleBean.kmip.codec.ttlv.serializer.kmip.common;

import org.purpleBean.kmip.codec.ttlv.serializer.AbstractKmipTtlvSerializer;
import org.purpleBean.kmip.common.CertificateSubjectAlternativeName;

public class CertificateSubjectAlternativeNameTtlvSerializer extends AbstractKmipTtlvSerializer<CertificateSubjectAlternativeName, String> {

    public CertificateSubjectAlternativeNameTtlvSerializer() {
        super(CertificateSubjectAlternativeName::getValue);
    }
}