package org.purpleBean.kmip.codec.ttlv.deserializer.kmip.common.enumeration;

import org.purpleBean.kmip.codec.ttlv.deserializer.AbstractKmipTtlvDeserializer;
import org.purpleBean.kmip.common.enumeration.PutFunction;

public class PutFunctionTtlvDeserializer extends AbstractKmipTtlvDeserializer<PutFunction, Integer> {

    public PutFunctionTtlvDeserializer() {
        super(PutFunction.kmipTag, PutFunction.encodingType, Integer.class, value -> new PutFunction(PutFunction.fromValue(value)));
    }
}