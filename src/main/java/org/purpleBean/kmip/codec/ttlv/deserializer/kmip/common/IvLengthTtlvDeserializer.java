package org.purpleBean.kmip.codec.ttlv.deserializer.kmip.common;

import org.purpleBean.kmip.codec.ttlv.deserializer.AbstractKmipTtlvDeserializer;
import org.purpleBean.kmip.common.IvLength;

public class IvLengthTtlvDeserializer extends AbstractKmipTtlvDeserializer<IvLength, Integer> {

    public IvLengthTtlvDeserializer() {
        super(IvLength.kmipTag, IvLength.encodingType, Integer.class, value -> IvLength.builder().value(value).build());
    }
}