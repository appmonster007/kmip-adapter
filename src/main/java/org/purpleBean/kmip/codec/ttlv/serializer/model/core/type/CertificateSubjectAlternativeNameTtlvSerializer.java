package org.purpleBean.kmip.codec.ttlv.serializer.model.core.type;

import org.purpleBean.kmip.codec.ttlv.serializer.kmip.AbstractKmipDataTypeTtlvSerializer;
import org.purpleBean.kmip.model.core.type.CertificateSubjectAlternativeName;

public class CertificateSubjectAlternativeNameTtlvSerializer extends AbstractKmipDataTypeTtlvSerializer<CertificateSubjectAlternativeName, String> {

    public CertificateSubjectAlternativeNameTtlvSerializer() {
        super(CertificateSubjectAlternativeName::getValue);
    }
}