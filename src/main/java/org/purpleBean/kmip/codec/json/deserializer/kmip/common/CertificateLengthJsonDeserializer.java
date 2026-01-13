package org.purpleBean.kmip.codec.json.deserializer.kmip.common;

import org.purpleBean.kmip.codec.json.deserializer.kmip.AbstractKmipDataTypeJsonDeserializer;
import org.purpleBean.kmip.common.CertificateLength;

public class CertificateLengthJsonDeserializer extends AbstractKmipDataTypeJsonDeserializer<CertificateLength, Integer> {

    public CertificateLengthJsonDeserializer() {
        super(CertificateLength.kmipTag, CertificateLength.encodingType, Integer.class, value -> CertificateLength.builder().value(value).build());
    }
}