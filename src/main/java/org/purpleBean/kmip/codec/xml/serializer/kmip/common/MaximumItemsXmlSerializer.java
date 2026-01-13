package org.purpleBean.kmip.codec.xml.serializer.kmip.common;

import org.purpleBean.kmip.codec.xml.serializer.kmip.AbstractKmipDataTypeXmlSerializer;
import org.purpleBean.kmip.common.MaximumItems;

public class MaximumItemsXmlSerializer extends AbstractKmipDataTypeXmlSerializer<MaximumItems, Integer> {

    public MaximumItemsXmlSerializer() {
        super(MaximumItems::getValue);
    }
}