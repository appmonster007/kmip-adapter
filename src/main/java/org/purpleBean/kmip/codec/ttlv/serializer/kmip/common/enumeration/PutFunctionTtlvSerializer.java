package org.purpleBean.kmip.codec.ttlv.serializer.kmip.common.enumeration;

import org.purpleBean.kmip.codec.ttlv.serializer.kmip.AbstractKmipDataTypeTtlvSerializer;
import org.purpleBean.kmip.common.enumeration.PutFunction;

public class PutFunctionTtlvSerializer extends AbstractKmipDataTypeTtlvSerializer<PutFunction, Integer> {

    public PutFunctionTtlvSerializer() {
        super(PutFunction::getValue);
    }
}