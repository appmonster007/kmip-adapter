package org.purpleBean.kmip.codec.ttlv.deserializer.model.core.type;

import org.purpleBean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purpleBean.kmip.model.core.type.CryptographicLength;

public class CryptographicLengthTtlvDeserializer extends AbstractKmipDataTypeTtlvDeserializer<CryptographicLength, Integer> {

    public CryptographicLengthTtlvDeserializer() {
        super(CryptographicLength.kmipTag, CryptographicLength.encodingType, Integer.class, value -> CryptographicLength.builder().value(value).build());
    }
}