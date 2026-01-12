package org.purpleBean.kmip.codec.ttlv.serializer.kmip.common.enumeration;

import org.purpleBean.kmip.codec.ttlv.serializer.AbstractKmipTtlvSerializer;
import org.purpleBean.kmip.common.enumeration.PaddingMethod;

public class PaddingMethodTtlvSerializer extends AbstractKmipTtlvSerializer<PaddingMethod, Integer> {

    public PaddingMethodTtlvSerializer() {
        super(value -> value.getValue().getValue());
    }
}