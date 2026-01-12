package org.purpleBean.kmip.codec.ttlv.deserializer.kmip.common.enumeration;

import org.purpleBean.kmip.codec.ttlv.deserializer.AbstractKmipTtlvDeserializer;
import org.purpleBean.kmip.common.enumeration.InteropFunction;

public class InteropFunctionTtlvDeserializer extends AbstractKmipTtlvDeserializer<InteropFunction, Integer> {

    public InteropFunctionTtlvDeserializer() {
        super(InteropFunction.kmipTag, InteropFunction.encodingType, Integer.class, value -> new InteropFunction(InteropFunction.fromValue(value)));
    }
}