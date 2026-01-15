package org.purpleBean.kmip.codec.json.serializer.model.core.type;

import org.purpleBean.kmip.codec.json.serializer.kmip.AbstractKmipDataTypeJsonSerializer;
import org.purpleBean.kmip.model.core.type.MaximumResponseSize;

public class MaximumResponseSizeJsonSerializer extends AbstractKmipDataTypeJsonSerializer<MaximumResponseSize, Integer> {

    public MaximumResponseSizeJsonSerializer() {
        super(MaximumResponseSize::getValue);
    }
}