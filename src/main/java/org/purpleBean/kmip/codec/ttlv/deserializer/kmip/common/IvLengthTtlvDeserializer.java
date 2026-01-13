package org.purpleBean.kmip.codec.ttlv.deserializer.kmip.common;

import org.purpleBean.kmip.codec.ttlv.deserializer.kmip.AbstractKmipDataTypeTtlvDeserializer;
import org.purpleBean.kmip.common.IvLength;

public class IvLengthTtlvDeserializer extends AbstractKmipDataTypeTtlvDeserializer<IvLength, Integer> {

    public IvLengthTtlvDeserializer() {
        super(IvLength.kmipTag, IvLength.encodingType, Integer.class, value -> IvLength.builder().value(value).build());
    }
}