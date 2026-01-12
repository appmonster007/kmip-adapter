package org.purpleBean.kmip.codec.json.serializer.kmip.common;

import org.purpleBean.kmip.codec.json.serializer.AbstractKmipJsonSerializer;
import org.purpleBean.kmip.common.MaximumItems;

public class MaximumItemsJsonSerializer extends AbstractKmipJsonSerializer<MaximumItems, Integer> {

    public MaximumItemsJsonSerializer() {
        super(MaximumItems::getValue);
    }
}