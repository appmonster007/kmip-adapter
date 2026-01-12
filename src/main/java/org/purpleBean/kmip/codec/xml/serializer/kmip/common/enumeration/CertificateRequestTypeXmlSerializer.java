package org.purpleBean.kmip.codec.xml.serializer.kmip.common.enumeration;

import org.purpleBean.kmip.codec.xml.serializer.AbstractKmipXmlSerializer;
import org.purpleBean.kmip.common.enumeration.CertificateRequestType;

public class CertificateRequestTypeXmlSerializer extends AbstractKmipXmlSerializer<CertificateRequestType, String> {

    public CertificateRequestTypeXmlSerializer() {
        super(CertificateRequestType::getDescription);
    }
}