package org.purpleBean.kmip.codec.xml.deserializer.model.core.type;

import org.purpleBean.kmip.codec.xml.deserializer.api.AbstractKmipDataTypeXmlDeserializer;
import org.purpleBean.kmip.model.core.type.MaximumItems;

public class MaximumItemsXmlDeserializer extends AbstractKmipDataTypeXmlDeserializer<MaximumItems, Integer> {

    public MaximumItemsXmlDeserializer() {
        super(MaximumItems.kmipTag, MaximumItems.encodingType, Integer.class, value -> MaximumItems.builder().value(value).build());
    }
}