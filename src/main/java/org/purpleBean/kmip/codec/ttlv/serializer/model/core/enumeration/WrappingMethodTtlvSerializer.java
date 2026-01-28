package org.purpleBean.kmip.codec.ttlv.serializer.model.core.enumeration;

import org.purpleBean.kmip.codec.ttlv.serializer.api.AbstractKmipDataTypeTtlvSerializer;
import org.purpleBean.kmip.model.core.enumeration.WrappingMethod;

public class WrappingMethodTtlvSerializer extends AbstractKmipDataTypeTtlvSerializer<WrappingMethod, Integer> {

    public WrappingMethodTtlvSerializer() {
        super(WrappingMethod::getIntValue);
    }
}