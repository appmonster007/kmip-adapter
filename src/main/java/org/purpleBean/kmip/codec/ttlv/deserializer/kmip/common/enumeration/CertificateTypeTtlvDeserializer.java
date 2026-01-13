package org.purpleBean.kmip.codec.ttlv.deserializer.kmip.common.enumeration;

import org.purpleBean.kmip.codec.ttlv.deserializer.kmip.AbstractKmipDataTypeTtlvDeserializer;
import org.purpleBean.kmip.common.enumeration.CertificateType;

public class CertificateTypeTtlvDeserializer extends AbstractKmipDataTypeTtlvDeserializer<CertificateType, Integer> {

    public CertificateTypeTtlvDeserializer() {
        super(CertificateType.kmipTag, CertificateType.encodingType, Integer.class, value -> new CertificateType(CertificateType.fromValue(value)));
    }
}