package org.purpleBean.kmip.codec.xml.deserializer.kmip.common.enumeration;

import org.purpleBean.kmip.codec.xml.deserializer.AbstractKmipXmlDeserializer;
import org.purpleBean.kmip.common.enumeration.CertificateRequestType;

public class CertificateRequestTypeXmlDeserializer extends AbstractKmipXmlDeserializer<CertificateRequestType, String> {

    public CertificateRequestTypeXmlDeserializer() {
        super(CertificateRequestType.kmipTag, CertificateRequestType.encodingType, String.class, value -> new CertificateRequestType(CertificateRequestType.fromName(value)));
    }
}