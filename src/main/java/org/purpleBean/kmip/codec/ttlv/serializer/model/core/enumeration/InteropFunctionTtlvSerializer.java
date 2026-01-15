package org.purpleBean.kmip.codec.ttlv.serializer.model.core.enumeration;

import org.purpleBean.kmip.codec.ttlv.serializer.kmip.AbstractKmipDataTypeTtlvSerializer;
import org.purpleBean.kmip.model.core.enumeration.InteropFunction;

public class InteropFunctionTtlvSerializer extends AbstractKmipDataTypeTtlvSerializer<InteropFunction, Integer> {

    public InteropFunctionTtlvSerializer() {
        super(InteropFunction::getValue);
    }
}