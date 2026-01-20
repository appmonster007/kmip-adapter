package org.purpleBean.kmip.codec.json.deserializer.model.core.enumeration;

import org.purpleBean.kmip.codec.json.deserializer.api.AbstractKmipDataTypeJsonDeserializer;
import org.purpleBean.kmip.model.core.enumeration.PutFunction;

public class PutFunctionJsonDeserializer extends AbstractKmipDataTypeJsonDeserializer<PutFunction, String> {

    public PutFunctionJsonDeserializer() {
        super(PutFunction.kmipTag, PutFunction.encodingType, String.class, value -> PutFunction.fromName(value).inst());
    }
}