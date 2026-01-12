package org.purpleBean.kmip.codec.ttlv.deserializer.kmip.common;

import org.purpleBean.kmip.codec.ttlv.deserializer.AbstractKmipTtlvDeserializer;
import org.purpleBean.kmip.common.CertificateSubjectDistinguishedName;

public class CertificateSubjectDistinguishedNameTtlvDeserializer extends AbstractKmipTtlvDeserializer<CertificateSubjectDistinguishedName, String> {

    public CertificateSubjectDistinguishedNameTtlvDeserializer() {
        super(CertificateSubjectDistinguishedName.kmipTag, CertificateSubjectDistinguishedName.encodingType, String.class, value -> CertificateSubjectDistinguishedName.builder().value(value).build());
    }
}