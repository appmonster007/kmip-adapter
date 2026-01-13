package org.purpleBean.kmip.codec.xml.serializer.kmip.common;

import org.purpleBean.kmip.codec.xml.serializer.kmip.AbstractKmipDataTypeXmlSerializer;
import org.purpleBean.kmip.common.CertificateSubjectAlternativeName;

public class CertificateSubjectAlternativeNameXmlSerializer extends AbstractKmipDataTypeXmlSerializer<CertificateSubjectAlternativeName, String> {

    public CertificateSubjectAlternativeNameXmlSerializer() {
        super(CertificateSubjectAlternativeName::getValue);
    }
}