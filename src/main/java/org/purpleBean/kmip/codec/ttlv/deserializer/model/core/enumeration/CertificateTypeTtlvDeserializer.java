package org.purpleBean.kmip.codec.ttlv.deserializer.model.core.enumeration;

import org.purpleBean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purpleBean.kmip.model.core.enumeration.CertificateType;

public class CertificateTypeTtlvDeserializer extends AbstractKmipDataTypeTtlvDeserializer<CertificateType, Integer> {

    public CertificateTypeTtlvDeserializer() {
        super(CertificateType.kmipTag, CertificateType.encodingType, Integer.class, value -> new CertificateType(CertificateType.fromValue(value)));
    }
}