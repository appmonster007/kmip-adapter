package org.purpleBean.kmip.codec.json.deserializer.kmip.common;

import org.purpleBean.kmip.codec.json.deserializer.kmip.AbstractKmipDataTypeJsonDeserializer;
import org.purpleBean.kmip.common.FixedFieldLength;

public class FixedFieldLengthJsonDeserializer extends AbstractKmipDataTypeJsonDeserializer<FixedFieldLength, Integer> {

    public FixedFieldLengthJsonDeserializer() {
        super(FixedFieldLength.kmipTag, FixedFieldLength.encodingType, Integer.class, value -> FixedFieldLength.builder().value(value).build());
    }
}