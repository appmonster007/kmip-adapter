package org.purpleBean.kmip.codec.json.deserializer.kmip.common.enumeration;

import org.purpleBean.kmip.codec.json.deserializer.AbstractKmipJsonDeserializer;
import org.purpleBean.kmip.common.enumeration.CertificateRequestType;

public class CertificateRequestTypeJsonDeserializer extends AbstractKmipJsonDeserializer<CertificateRequestType, String> {

    public CertificateRequestTypeJsonDeserializer() {
        super(CertificateRequestType.kmipTag, CertificateRequestType.encodingType, String.class, value -> new CertificateRequestType(CertificateRequestType.fromName(value)));
    }
}