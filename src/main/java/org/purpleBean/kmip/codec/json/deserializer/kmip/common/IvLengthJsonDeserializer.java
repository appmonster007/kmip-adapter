package org.purpleBean.kmip.codec.json.deserializer.kmip.common;

import org.purpleBean.kmip.codec.json.deserializer.kmip.AbstractKmipDataTypeJsonDeserializer;
import org.purpleBean.kmip.common.IvLength;

public class IvLengthJsonDeserializer extends AbstractKmipDataTypeJsonDeserializer<IvLength, Integer> {

    public IvLengthJsonDeserializer() {
        super(IvLength.kmipTag, IvLength.encodingType, Integer.class, value -> IvLength.builder().value(value).build());
    }
}