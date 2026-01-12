package org.purpleBean.kmip.codec.ttlv.serializer.kmip.common.enumeration;

import org.purpleBean.kmip.codec.ttlv.serializer.AbstractKmipTtlvSerializer;
import org.purpleBean.kmip.common.enumeration.PutFunction;

public class PutFunctionTtlvSerializer extends AbstractKmipTtlvSerializer<PutFunction, Integer> {

    public PutFunctionTtlvSerializer() {
        super(value -> value.getValue().getValue());
    }
}