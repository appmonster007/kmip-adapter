package org.purpleBean.kmip.codec.xml.serializer.model.core.type;

import org.purpleBean.kmip.codec.xml.serializer.kmip.AbstractKmipDataTypeXmlSerializer;
import org.purpleBean.kmip.model.core.type.CertificateSubjectDistinguishedName;

public class CertificateSubjectDistinguishedNameXmlSerializer extends AbstractKmipDataTypeXmlSerializer<CertificateSubjectDistinguishedName, String> {

    public CertificateSubjectDistinguishedNameXmlSerializer() {
        super(CertificateSubjectDistinguishedName::getValue);
    }
}