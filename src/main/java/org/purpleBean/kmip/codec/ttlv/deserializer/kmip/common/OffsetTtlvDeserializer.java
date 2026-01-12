package org.purpleBean.kmip.codec.ttlv.deserializer.kmip.common;

import org.purpleBean.kmip.codec.ttlv.deserializer.AbstractKmipTtlvDeserializer;
import org.purpleBean.kmip.common.Offset;

public class OffsetTtlvDeserializer extends AbstractKmipTtlvDeserializer<Offset, Integer> {

    public OffsetTtlvDeserializer() {
        super(Offset.kmipTag, Offset.encodingType, Integer.class, value -> Offset.builder().value(value).build());
    }
}