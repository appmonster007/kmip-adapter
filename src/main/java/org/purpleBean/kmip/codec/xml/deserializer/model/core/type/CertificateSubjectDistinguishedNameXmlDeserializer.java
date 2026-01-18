package org.purpleBean.kmip.codec.xml.deserializer.model.core.type;

import org.purpleBean.kmip.codec.xml.deserializer.api.AbstractKmipDataTypeXmlDeserializer;
import org.purpleBean.kmip.model.core.type.CertificateSubjectDistinguishedName;

public class CertificateSubjectDistinguishedNameXmlDeserializer extends AbstractKmipDataTypeXmlDeserializer<CertificateSubjectDistinguishedName, String> {

    public CertificateSubjectDistinguishedNameXmlDeserializer() {
        super(CertificateSubjectDistinguishedName.kmipTag, CertificateSubjectDistinguishedName.encodingType, String.class, value -> CertificateSubjectDistinguishedName.builder().value(value).build());
    }
}