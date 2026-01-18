package org.purpleBean.kmip.codec.json.deserializer.model.core.type;

import org.purpleBean.kmip.codec.json.deserializer.api.AbstractKmipDataTypeJsonDeserializer;
import org.purpleBean.kmip.model.core.type.InvocationFieldLength;

public class InvocationFieldLengthJsonDeserializer extends AbstractKmipDataTypeJsonDeserializer<InvocationFieldLength, Integer> {

    public InvocationFieldLengthJsonDeserializer() {
        super(InvocationFieldLength.kmipTag, InvocationFieldLength.encodingType, Integer.class, value -> InvocationFieldLength.builder().value(value).build());
    }
}