package org.purpleBean.kmip.codec.json.deserializer.model.core.enumeration;

import org.purpleBean.kmip.codec.json.deserializer.api.AbstractKmipDataTypeJsonDeserializer;
import org.purpleBean.kmip.model.core.enumeration.InteropFunction;

public class InteropFunctionJsonDeserializer extends AbstractKmipDataTypeJsonDeserializer<InteropFunction, String> {

    public InteropFunctionJsonDeserializer() {
        super(InteropFunction.kmipTag, InteropFunction.encodingType, String.class, value -> InteropFunction.fromName(value).inst());
    }
}