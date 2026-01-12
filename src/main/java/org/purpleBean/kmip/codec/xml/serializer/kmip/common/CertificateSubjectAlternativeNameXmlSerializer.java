package org.purpleBean.kmip.codec.xml.serializer.kmip.common;

import org.purpleBean.kmip.common.CertificateSubjectAlternativeName;
import org.purpleBean.kmip.codec.xml.serializer.AbstractKmipXmlSerializer;

public class CertificateSubjectAlternativeNameXmlSerializer extends AbstractKmipXmlSerializer<CertificateSubjectAlternativeName, String> {

    public CertificateSubjectAlternativeNameXmlSerializer() {
        super(CertificateSubjectAlternativeName::getValue);
    }
}