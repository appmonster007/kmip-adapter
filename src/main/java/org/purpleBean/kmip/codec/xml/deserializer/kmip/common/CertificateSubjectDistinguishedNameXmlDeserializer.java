package org.purpleBean.kmip.codec.xml.deserializer.kmip.common;

import org.purpleBean.kmip.codec.xml.deserializer.kmip.AbstractKmipDataTypeXmlDeserializer;
import org.purpleBean.kmip.common.CertificateSubjectDistinguishedName;

public class CertificateSubjectDistinguishedNameXmlDeserializer extends AbstractKmipDataTypeXmlDeserializer<CertificateSubjectDistinguishedName, String> {

    public CertificateSubjectDistinguishedNameXmlDeserializer() {
        super(CertificateSubjectDistinguishedName.kmipTag, CertificateSubjectDistinguishedName.encodingType, String.class, value -> CertificateSubjectDistinguishedName.builder().value(value).build());
    }
}