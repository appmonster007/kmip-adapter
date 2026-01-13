package org.purpleBean.kmip.codec.json.deserializer.kmip.common;

import org.purpleBean.kmip.codec.json.deserializer.kmip.AbstractKmipDataTypeJsonDeserializer;
import org.purpleBean.kmip.common.CertificateSubjectDistinguishedName;

public class CertificateSubjectDistinguishedNameJsonDeserializer extends AbstractKmipDataTypeJsonDeserializer<CertificateSubjectDistinguishedName, String> {

    public CertificateSubjectDistinguishedNameJsonDeserializer() {
        super(CertificateSubjectDistinguishedName.kmipTag, CertificateSubjectDistinguishedName.encodingType, String.class, value -> CertificateSubjectDistinguishedName.builder().value(value).build());
    }
}