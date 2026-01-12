package org.purpleBean.kmip.codec.ttlv.deserializer.kmip.common;

import org.purpleBean.kmip.codec.ttlv.deserializer.AbstractKmipTtlvDeserializer;
import org.purpleBean.kmip.common.CertificateLength;

public class CertificateLengthTtlvDeserializer extends AbstractKmipTtlvDeserializer<CertificateLength, Integer> {

    public CertificateLengthTtlvDeserializer() {
        super(CertificateLength.kmipTag, CertificateLength.encodingType, Integer.class, value -> CertificateLength.builder().value(value).build());
    }
}