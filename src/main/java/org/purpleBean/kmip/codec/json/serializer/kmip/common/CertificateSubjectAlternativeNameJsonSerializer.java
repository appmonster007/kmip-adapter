package org.purpleBean.kmip.codec.json.serializer.kmip.common;

import org.purpleBean.kmip.codec.json.serializer.kmip.AbstractKmipDataTypeJsonSerializer;
import org.purpleBean.kmip.common.CertificateSubjectAlternativeName;

public class CertificateSubjectAlternativeNameJsonSerializer extends AbstractKmipDataTypeJsonSerializer<CertificateSubjectAlternativeName, String> {

    public CertificateSubjectAlternativeNameJsonSerializer() {
        super(CertificateSubjectAlternativeName::getValue);
    }
}