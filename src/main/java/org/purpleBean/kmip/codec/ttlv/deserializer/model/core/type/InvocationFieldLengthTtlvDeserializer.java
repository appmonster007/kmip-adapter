package org.purpleBean.kmip.codec.ttlv.deserializer.model.core.type;

import org.purpleBean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purpleBean.kmip.model.core.type.InvocationFieldLength;

public class InvocationFieldLengthTtlvDeserializer extends AbstractKmipDataTypeTtlvDeserializer<InvocationFieldLength, Integer> {

    public InvocationFieldLengthTtlvDeserializer() {
        super(InvocationFieldLength.kmipTag, InvocationFieldLength.encodingType, Integer.class, value -> InvocationFieldLength.builder().value(value).build());
    }
}