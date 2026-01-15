package org.purpleBean.kmip.codec.json.serializer.model.core.type;

import org.purpleBean.kmip.codec.json.serializer.kmip.AbstractKmipDataTypeJsonSerializer;
import org.purpleBean.kmip.model.core.type.CertificateSubjectDistinguishedName;

public class CertificateSubjectDistinguishedNameJsonSerializer extends AbstractKmipDataTypeJsonSerializer<CertificateSubjectDistinguishedName, String> {

    public CertificateSubjectDistinguishedNameJsonSerializer() {
        super(CertificateSubjectDistinguishedName::getValue);
    }
}