package org.purpleBean.kmip.codec.xml.deserializer.kmip.common;

import org.purpleBean.kmip.codec.xml.deserializer.AbstractKmipXmlDeserializer;
import org.purpleBean.kmip.common.CertificateSubjectAlternativeName;

public class CertificateSubjectAlternativeNameXmlDeserializer extends AbstractKmipXmlDeserializer<CertificateSubjectAlternativeName, String> {

    public CertificateSubjectAlternativeNameXmlDeserializer() {
        super(CertificateSubjectAlternativeName.kmipTag, CertificateSubjectAlternativeName.encodingType, String.class, value -> CertificateSubjectAlternativeName.builder().value(value).build());
    }
}