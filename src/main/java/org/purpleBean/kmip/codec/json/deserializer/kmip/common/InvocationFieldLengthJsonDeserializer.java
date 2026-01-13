package org.purpleBean.kmip.codec.json.deserializer.kmip.common;

import org.purpleBean.kmip.codec.json.deserializer.kmip.AbstractKmipDataTypeJsonDeserializer;
import org.purpleBean.kmip.common.InvocationFieldLength;

public class InvocationFieldLengthJsonDeserializer extends AbstractKmipDataTypeJsonDeserializer<InvocationFieldLength, Integer> {

    public InvocationFieldLengthJsonDeserializer() {
        super(InvocationFieldLength.kmipTag, InvocationFieldLength.encodingType, Integer.class, value -> InvocationFieldLength.builder().value(value).build());
    }
}