package org.purpleBean.kmip.codec.xml.serializer.model.core.type;

import org.purpleBean.kmip.codec.xml.serializer.kmip.AbstractKmipDataTypeXmlSerializer;
import org.purpleBean.kmip.model.core.type.CertificateSubjectAlternativeName;

public class CertificateSubjectAlternativeNameXmlSerializer extends AbstractKmipDataTypeXmlSerializer<CertificateSubjectAlternativeName, String> {

    public CertificateSubjectAlternativeNameXmlSerializer() {
        super(CertificateSubjectAlternativeName::getValue);
    }
}