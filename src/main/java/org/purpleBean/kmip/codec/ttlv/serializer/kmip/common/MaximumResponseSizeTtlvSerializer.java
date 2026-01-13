package org.purpleBean.kmip.codec.ttlv.serializer.kmip.common;

import org.purpleBean.kmip.codec.ttlv.serializer.kmip.AbstractKmipDataTypeTtlvSerializer;
import org.purpleBean.kmip.common.MaximumResponseSize;

public class MaximumResponseSizeTtlvSerializer extends AbstractKmipDataTypeTtlvSerializer<MaximumResponseSize, Integer> {

    public MaximumResponseSizeTtlvSerializer() {
        super(MaximumResponseSize::getValue);
    }
}