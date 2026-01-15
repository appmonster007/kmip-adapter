package org.purpleBean.kmip.codec.xml.serializer.model.core.enumeration;

import org.purpleBean.kmip.codec.xml.serializer.kmip.AbstractKmipDataTypeXmlSerializer;
import org.purpleBean.kmip.model.core.enumeration.CertificateRequestType;

public class CertificateRequestTypeXmlSerializer extends AbstractKmipDataTypeXmlSerializer<CertificateRequestType, String> {

    public CertificateRequestTypeXmlSerializer() {
        super(CertificateRequestType::getDescription);
    }
}