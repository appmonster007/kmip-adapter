package org.purpleBean.kmip.codec.json.deserializer.kmip.common;

import org.purpleBean.kmip.codec.json.deserializer.AbstractKmipJsonDeserializer;
import org.purpleBean.kmip.common.IvLength;

public class IvLengthJsonDeserializer extends AbstractKmipJsonDeserializer<IvLength, Integer> {

    public IvLengthJsonDeserializer() {
        super(IvLength.kmipTag, IvLength.encodingType, Integer.class, value -> IvLength.builder().value(value).build());
    }
}