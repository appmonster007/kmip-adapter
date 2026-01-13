package org.purpleBean.kmip.codec.json.deserializer.kmip.common;

import org.purpleBean.kmip.codec.json.deserializer.kmip.AbstractKmipDataTypeJsonDeserializer;
import org.purpleBean.kmip.common.Offset;

public class OffsetJsonDeserializer extends AbstractKmipDataTypeJsonDeserializer<Offset, Integer> {

    public OffsetJsonDeserializer() {
        super(Offset.kmipTag, Offset.encodingType, Integer.class, value -> Offset.builder().value(value).build());
    }
}