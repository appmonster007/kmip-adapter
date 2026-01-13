package org.purpleBean.kmip.codec.json.deserializer.kmip.common;

import org.purpleBean.kmip.codec.json.deserializer.kmip.AbstractKmipDataTypeJsonDeserializer;
import org.purpleBean.kmip.common.MaximumItems;

public class MaximumItemsJsonDeserializer extends AbstractKmipDataTypeJsonDeserializer<MaximumItems, Integer> {

    public MaximumItemsJsonDeserializer() {
        super(MaximumItems.kmipTag, MaximumItems.encodingType, Integer.class, value -> MaximumItems.builder().value(value).build());
    }
}