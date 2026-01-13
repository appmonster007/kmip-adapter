package org.purpleBean.kmip.codec.json.deserializer.kmip.common.enumeration;

import org.purpleBean.kmip.codec.json.deserializer.kmip.AbstractKmipDataTypeJsonDeserializer;
import org.purpleBean.kmip.common.enumeration.PutFunction;

public class PutFunctionJsonDeserializer extends AbstractKmipDataTypeJsonDeserializer<PutFunction, String> {

    public PutFunctionJsonDeserializer() {
        super(PutFunction.kmipTag, PutFunction.encodingType, String.class, value -> new PutFunction(PutFunction.fromName(value)));
    }
}