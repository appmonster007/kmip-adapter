package org.purpleBean.kmip.codec.ttlv.serializer.kmip.common.enumeration;

import org.purpleBean.kmip.codec.ttlv.serializer.AbstractKmipTtlvSerializer;
import org.purpleBean.kmip.common.enumeration.WrappingMethod;

public class WrappingMethodTtlvSerializer extends AbstractKmipTtlvSerializer<WrappingMethod, Integer> {

    public WrappingMethodTtlvSerializer() {
        super(value -> value.getValue().getValue());
    }
}