package org.purpleBean.kmip.codec.ttlv.serializer.model.core.type;

import org.purpleBean.kmip.codec.ttlv.serializer.kmip.AbstractKmipDataTypeTtlvSerializer;
import org.purpleBean.kmip.model.core.type.MaximumResponseSize;

public class MaximumResponseSizeTtlvSerializer extends AbstractKmipDataTypeTtlvSerializer<MaximumResponseSize, Integer> {

    public MaximumResponseSizeTtlvSerializer() {
        super(MaximumResponseSize::getValue);
    }
}