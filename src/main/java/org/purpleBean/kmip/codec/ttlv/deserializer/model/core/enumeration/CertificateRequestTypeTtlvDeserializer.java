package org.purpleBean.kmip.codec.ttlv.deserializer.model.core.enumeration;

import org.purpleBean.kmip.codec.ttlv.deserializer.kmip.AbstractKmipDataTypeTtlvDeserializer;
import org.purpleBean.kmip.model.core.enumeration.CertificateRequestType;

public class CertificateRequestTypeTtlvDeserializer extends AbstractKmipDataTypeTtlvDeserializer<CertificateRequestType, Integer> {

    public CertificateRequestTypeTtlvDeserializer() {
        super(CertificateRequestType.kmipTag, CertificateRequestType.encodingType, Integer.class, value -> new CertificateRequestType(CertificateRequestType.fromValue(value)));
    }
}