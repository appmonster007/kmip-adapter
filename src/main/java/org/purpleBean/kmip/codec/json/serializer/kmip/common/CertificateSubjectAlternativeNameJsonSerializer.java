package org.purpleBean.kmip.codec.json.serializer.kmip.common;

import org.purpleBean.kmip.codec.json.serializer.AbstractKmipJsonSerializer;
import org.purpleBean.kmip.common.CertificateSubjectAlternativeName;

public class CertificateSubjectAlternativeNameJsonSerializer extends AbstractKmipJsonSerializer<CertificateSubjectAlternativeName, String> {

    public CertificateSubjectAlternativeNameJsonSerializer() {
        super(CertificateSubjectAlternativeName::getValue);
    }
}