package org.purpleBean.kmip.codec.ttlv.deserializer.model.core.type;

import org.purpleBean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purpleBean.kmip.model.core.type.MaximumItems;

public class MaximumItemsTtlvDeserializer extends AbstractKmipDataTypeTtlvDeserializer<MaximumItems, Integer> {

    public MaximumItemsTtlvDeserializer() {
        super(MaximumItems.kmipTag, MaximumItems.encodingType, Integer.class, value -> MaximumItems.builder().value(value).build());
    }
}