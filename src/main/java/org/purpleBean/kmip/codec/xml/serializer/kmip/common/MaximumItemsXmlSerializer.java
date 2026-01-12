package org.purpleBean.kmip.codec.xml.serializer.kmip.common;

import org.purpleBean.kmip.codec.xml.serializer.AbstractKmipXmlSerializer;
import org.purpleBean.kmip.common.MaximumItems;

public class MaximumItemsXmlSerializer extends AbstractKmipXmlSerializer<MaximumItems, Integer> {

    public MaximumItemsXmlSerializer() {
        super(MaximumItems::getValue);
    }
}