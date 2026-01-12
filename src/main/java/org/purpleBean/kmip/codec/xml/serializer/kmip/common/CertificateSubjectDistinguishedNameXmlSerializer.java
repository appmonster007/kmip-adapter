package org.purpleBean.kmip.codec.xml.serializer.kmip.common;

import org.purpleBean.kmip.common.CertificateSubjectDistinguishedName;
import org.purpleBean.kmip.codec.xml.serializer.AbstractKmipXmlSerializer;
import java.nio.ByteBuffer;

public class CertificateSubjectDistinguishedNameXmlSerializer extends AbstractKmipXmlSerializer<CertificateSubjectDistinguishedName, String> {

    public CertificateSubjectDistinguishedNameXmlSerializer() {
        super(CertificateSubjectDistinguishedName::getValue);
    }
}