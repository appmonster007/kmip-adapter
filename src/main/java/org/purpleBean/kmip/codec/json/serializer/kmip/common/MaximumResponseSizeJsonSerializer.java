package org.purpleBean.kmip.codec.json.serializer.kmip.common;

import org.purpleBean.kmip.codec.json.serializer.AbstractKmipJsonSerializer;
import org.purpleBean.kmip.common.MaximumResponseSize;

public class MaximumResponseSizeJsonSerializer extends AbstractKmipJsonSerializer<MaximumResponseSize, Integer> {

    public MaximumResponseSizeJsonSerializer() {
        super(MaximumResponseSize::getValue);
    }
}