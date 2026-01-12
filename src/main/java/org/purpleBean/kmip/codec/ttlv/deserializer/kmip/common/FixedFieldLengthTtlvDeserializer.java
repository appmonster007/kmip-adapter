package org.purpleBean.kmip.codec.ttlv.deserializer.kmip.common;

import org.purpleBean.kmip.codec.ttlv.deserializer.AbstractKmipTtlvDeserializer;
import org.purpleBean.kmip.common.FixedFieldLength;

public class FixedFieldLengthTtlvDeserializer extends AbstractKmipTtlvDeserializer<FixedFieldLength, Integer> {

    public FixedFieldLengthTtlvDeserializer() {
        super(FixedFieldLength.kmipTag, FixedFieldLength.encodingType, Integer.class, value -> FixedFieldLength.builder().value(value).build());
    }
}