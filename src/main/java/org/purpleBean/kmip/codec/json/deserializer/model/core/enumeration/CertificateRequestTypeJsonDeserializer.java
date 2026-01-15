package org.purpleBean.kmip.codec.json.deserializer.model.core.enumeration;

import org.purpleBean.kmip.codec.json.deserializer.kmip.AbstractKmipDataTypeJsonDeserializer;
import org.purpleBean.kmip.model.core.enumeration.CertificateRequestType;

public class CertificateRequestTypeJsonDeserializer extends AbstractKmipDataTypeJsonDeserializer<CertificateRequestType, String> {

    public CertificateRequestTypeJsonDeserializer() {
        super(CertificateRequestType.kmipTag, CertificateRequestType.encodingType, String.class, value -> new CertificateRequestType(CertificateRequestType.fromName(value)));
    }
}