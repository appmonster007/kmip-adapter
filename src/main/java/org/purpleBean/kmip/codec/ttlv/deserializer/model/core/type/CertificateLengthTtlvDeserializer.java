package org.purpleBean.kmip.codec.ttlv.deserializer.model.core.type;

import org.purpleBean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purpleBean.kmip.model.core.type.CertificateLength;

public class CertificateLengthTtlvDeserializer extends AbstractKmipDataTypeTtlvDeserializer<CertificateLength, Integer> {

    public CertificateLengthTtlvDeserializer() {
        super(CertificateLength.kmipTag, CertificateLength.encodingType, Integer.class, value -> CertificateLength.builder().value(value).build());
    }
}