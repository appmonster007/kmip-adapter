package org.purpleBean.kmip.codec.ttlv.deserializer.kmip.common;

import org.purpleBean.kmip.codec.ttlv.deserializer.kmip.AbstractKmipDataTypeTtlvDeserializer;
import org.purpleBean.kmip.common.CryptographicLength;

public class CryptographicLengthTtlvDeserializer extends AbstractKmipDataTypeTtlvDeserializer<CryptographicLength, Integer> {

    public CryptographicLengthTtlvDeserializer() {
        super(CryptographicLength.kmipTag, CryptographicLength.encodingType, Integer.class, value -> CryptographicLength.builder().value(value).build());
    }
}