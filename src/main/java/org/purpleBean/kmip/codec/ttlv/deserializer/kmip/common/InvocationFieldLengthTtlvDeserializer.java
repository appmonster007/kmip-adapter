package org.purpleBean.kmip.codec.ttlv.deserializer.kmip.common;

import org.purpleBean.kmip.codec.ttlv.deserializer.kmip.AbstractKmipDataTypeTtlvDeserializer;
import org.purpleBean.kmip.common.InvocationFieldLength;

public class InvocationFieldLengthTtlvDeserializer extends AbstractKmipDataTypeTtlvDeserializer<InvocationFieldLength, Integer> {

    public InvocationFieldLengthTtlvDeserializer() {
        super(InvocationFieldLength.kmipTag, InvocationFieldLength.encodingType, Integer.class, value -> InvocationFieldLength.builder().value(value).build());
    }
}