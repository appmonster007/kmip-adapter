package org.purpleBean.kmip.codec.json.serializer.kmip.common;

import org.purpleBean.kmip.codec.json.serializer.kmip.AbstractKmipDataTypeJsonSerializer;
import org.purpleBean.kmip.common.CertificateSubjectDistinguishedName;

public class CertificateSubjectDistinguishedNameJsonSerializer extends AbstractKmipDataTypeJsonSerializer<CertificateSubjectDistinguishedName, String> {

    public CertificateSubjectDistinguishedNameJsonSerializer() {
        super(CertificateSubjectDistinguishedName::getValue);
    }
}