package org.purpleBean.kmip.codec.ttlv.serializer.kmip.common;

import org.purpleBean.kmip.codec.ttlv.serializer.AbstractKmipTtlvSerializer;
import org.purpleBean.kmip.common.MaximumResponseSize;

public class MaximumResponseSizeTtlvSerializer extends AbstractKmipTtlvSerializer<MaximumResponseSize, Integer> {

    public MaximumResponseSizeTtlvSerializer() {
        super(MaximumResponseSize::getValue);
    }
}