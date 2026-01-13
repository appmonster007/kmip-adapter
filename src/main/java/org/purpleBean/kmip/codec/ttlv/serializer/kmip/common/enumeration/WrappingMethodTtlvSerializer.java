package org.purpleBean.kmip.codec.ttlv.serializer.kmip.common.enumeration;

import org.purpleBean.kmip.codec.ttlv.serializer.kmip.AbstractKmipDataTypeTtlvSerializer;
import org.purpleBean.kmip.common.enumeration.WrappingMethod;

public class WrappingMethodTtlvSerializer extends AbstractKmipDataTypeTtlvSerializer<WrappingMethod, Integer> {

    public WrappingMethodTtlvSerializer() {
        super(value -> value.getValue().getValue());
    }
}