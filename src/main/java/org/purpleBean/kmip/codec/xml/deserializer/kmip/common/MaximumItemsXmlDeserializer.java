package org.purpleBean.kmip.codec.xml.deserializer.kmip.common;

import org.purpleBean.kmip.codec.xml.deserializer.kmip.AbstractKmipDataTypeXmlDeserializer;
import org.purpleBean.kmip.common.MaximumItems;

public class MaximumItemsXmlDeserializer extends AbstractKmipDataTypeXmlDeserializer<MaximumItems, Integer> {

    public MaximumItemsXmlDeserializer() {
        super(MaximumItems.kmipTag, MaximumItems.encodingType, Integer.class, value -> MaximumItems.builder().value(value).build());
    }
}