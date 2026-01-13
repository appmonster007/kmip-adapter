package org.purpleBean.kmip.codec.ttlv.serializer.kmip.common.enumeration;

import org.purpleBean.kmip.codec.ttlv.serializer.kmip.AbstractKmipDataTypeTtlvSerializer;
import org.purpleBean.kmip.common.enumeration.InteropFunction;

public class InteropFunctionTtlvSerializer extends AbstractKmipDataTypeTtlvSerializer<InteropFunction, Integer> {

    public InteropFunctionTtlvSerializer() {
        super(value -> value.getValue().getValue());
    }
}