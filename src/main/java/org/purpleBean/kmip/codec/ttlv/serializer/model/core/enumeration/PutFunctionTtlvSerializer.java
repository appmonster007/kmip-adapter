package org.purpleBean.kmip.codec.ttlv.serializer.model.core.enumeration;

import org.purpleBean.kmip.codec.ttlv.serializer.kmip.AbstractKmipDataTypeTtlvSerializer;
import org.purpleBean.kmip.model.core.enumeration.PutFunction;

public class PutFunctionTtlvSerializer extends AbstractKmipDataTypeTtlvSerializer<PutFunction, Integer> {

    public PutFunctionTtlvSerializer() {
        super(PutFunction::getValue);
    }
}