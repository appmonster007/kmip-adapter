package org.purpleBean.kmip.codec.json.deserializer.kmip.common.enumeration;

import org.purpleBean.kmip.codec.json.deserializer.kmip.AbstractKmipDataTypeJsonDeserializer;
import org.purpleBean.kmip.common.enumeration.InteropFunction;

public class InteropFunctionJsonDeserializer extends AbstractKmipDataTypeJsonDeserializer<InteropFunction, String> {

    public InteropFunctionJsonDeserializer() {
        super(InteropFunction.kmipTag, InteropFunction.encodingType, String.class, value -> new InteropFunction(InteropFunction.fromName(value)));
    }
}