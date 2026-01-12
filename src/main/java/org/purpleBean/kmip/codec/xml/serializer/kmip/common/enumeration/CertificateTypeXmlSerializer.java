package org.purpleBean.kmip.codec.xml.serializer.kmip.common.enumeration;

import org.purpleBean.kmip.common.enumeration.CertificateType;
import org.purpleBean.kmip.codec.xml.serializer.AbstractKmipXmlSerializer;

public class CertificateTypeXmlSerializer extends AbstractKmipXmlSerializer<CertificateType, String> {

    public CertificateTypeXmlSerializer() {
        super(CertificateType::getDescription);
    }
}