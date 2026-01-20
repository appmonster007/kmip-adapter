package org.purpleBean.kmip.codec.xml.deserializer.model.core.enumeration;

import org.purpleBean.kmip.codec.xml.deserializer.api.AbstractKmipDataTypeXmlDeserializer;
import org.purpleBean.kmip.model.core.enumeration.CertificateRequestType;

public class CertificateRequestTypeXmlDeserializer extends AbstractKmipDataTypeXmlDeserializer<CertificateRequestType, String> {

    public CertificateRequestTypeXmlDeserializer() {
        super(CertificateRequestType.kmipTag, CertificateRequestType.encodingType, String.class, value -> CertificateRequestType.fromName(value).inst());
    }
}