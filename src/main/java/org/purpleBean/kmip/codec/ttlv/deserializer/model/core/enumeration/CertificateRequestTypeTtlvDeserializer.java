package org.purpleBean.kmip.codec.ttlv.deserializer.model.core.enumeration;

import org.purpleBean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purpleBean.kmip.model.core.enumeration.CertificateRequestType;

public class CertificateRequestTypeTtlvDeserializer extends AbstractKmipDataTypeTtlvDeserializer<CertificateRequestType, Integer> {

    public CertificateRequestTypeTtlvDeserializer() {
        super(CertificateRequestType.kmipTag, CertificateRequestType.encodingType, Integer.class, value -> CertificateRequestType.fromValue(value).inst());
    }
}