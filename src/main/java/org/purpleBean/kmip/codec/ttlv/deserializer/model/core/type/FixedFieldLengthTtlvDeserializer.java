package org.purpleBean.kmip.codec.ttlv.deserializer.model.core.type;

import org.purpleBean.kmip.codec.ttlv.deserializer.kmip.AbstractKmipDataTypeTtlvDeserializer;
import org.purpleBean.kmip.model.core.type.FixedFieldLength;

public class FixedFieldLengthTtlvDeserializer extends AbstractKmipDataTypeTtlvDeserializer<FixedFieldLength, Integer> {

    public FixedFieldLengthTtlvDeserializer() {
        super(FixedFieldLength.kmipTag, FixedFieldLength.encodingType, Integer.class, value -> FixedFieldLength.builder().value(value).build());
    }
}