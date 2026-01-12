package org.purpleBean.kmip.codec.xml.serializer.kmip.common;

import org.purpleBean.kmip.codec.xml.serializer.AbstractKmipXmlSerializer;
import org.purpleBean.kmip.common.CertificateSubjectAlternativeName;

public class CertificateSubjectAlternativeNameXmlSerializer extends AbstractKmipXmlSerializer<CertificateSubjectAlternativeName, String> {

    public CertificateSubjectAlternativeNameXmlSerializer() {
        super(CertificateSubjectAlternativeName::getValue);
    }
}