package org.purpleBean.kmip.codec.json.serializer.model.core.type;

import org.purpleBean.kmip.codec.json.serializer.kmip.AbstractKmipDataTypeJsonSerializer;
import org.purpleBean.kmip.model.core.type.MaximumItems;

public class MaximumItemsJsonSerializer extends AbstractKmipDataTypeJsonSerializer<MaximumItems, Integer> {

    public MaximumItemsJsonSerializer() {
        super(MaximumItems::getValue);
    }
}