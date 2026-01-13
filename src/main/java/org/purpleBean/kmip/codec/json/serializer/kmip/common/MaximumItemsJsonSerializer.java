package org.purpleBean.kmip.codec.json.serializer.kmip.common;

import org.purpleBean.kmip.codec.json.serializer.kmip.AbstractKmipDataTypeJsonSerializer;
import org.purpleBean.kmip.common.MaximumItems;

public class MaximumItemsJsonSerializer extends AbstractKmipDataTypeJsonSerializer<MaximumItems, Integer> {

    public MaximumItemsJsonSerializer() {
        super(MaximumItems::getValue);
    }
}