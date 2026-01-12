package org.purpleBean.kmip.codec.ttlv.deserializer.kmip.common.enumeration;

import org.purpleBean.kmip.codec.ttlv.deserializer.AbstractKmipTtlvDeserializer;
import org.purpleBean.kmip.common.enumeration.CertificateRequestType;

public class CertificateRequestTypeTtlvDeserializer extends AbstractKmipTtlvDeserializer<CertificateRequestType, Integer> {

    public CertificateRequestTypeTtlvDeserializer() {
        super(CertificateRequestType.kmipTag, CertificateRequestType.encodingType, Integer.class, value -> new CertificateRequestType(CertificateRequestType.fromValue(value)));
    }
}