package org.purpleBean.kmip.codec.xml.serializer.kmip.common;

import org.purpleBean.kmip.codec.xml.serializer.kmip.AbstractKmipDataTypeXmlSerializer;
import org.purpleBean.kmip.common.CertificateSubjectDistinguishedName;

public class CertificateSubjectDistinguishedNameXmlSerializer extends AbstractKmipDataTypeXmlSerializer<CertificateSubjectDistinguishedName, String> {

    public CertificateSubjectDistinguishedNameXmlSerializer() {
        super(CertificateSubjectDistinguishedName::getValue);
    }
}