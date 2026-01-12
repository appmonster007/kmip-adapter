package org.purpleBean.kmip.codec.ttlv.deserializer.kmip.common;

import org.purpleBean.kmip.codec.ttlv.deserializer.AbstractKmipTtlvDeserializer;
import org.purpleBean.kmip.common.CryptographicLength;

public class CryptographicLengthTtlvDeserializer extends AbstractKmipTtlvDeserializer<CryptographicLength, Integer> {

    public CryptographicLengthTtlvDeserializer() {
        super(CryptographicLength.kmipTag, CryptographicLength.encodingType, Integer.class, value -> CryptographicLength.builder().value(value).build());
    }
}