package org.purpleBean.kmip.codec.ttlv.deserializer.model.core.enumeration;

import org.purpleBean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purpleBean.kmip.model.core.enumeration.PutFunction;

public class PutFunctionTtlvDeserializer extends AbstractKmipDataTypeTtlvDeserializer<PutFunction, Integer> {

    public PutFunctionTtlvDeserializer() {
        super(PutFunction.kmipTag, PutFunction.encodingType, Integer.class, value -> PutFunction.fromValue(value).inst());
    }
}