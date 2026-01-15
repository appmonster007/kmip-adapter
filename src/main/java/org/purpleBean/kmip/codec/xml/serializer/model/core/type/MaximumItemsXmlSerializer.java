package org.purpleBean.kmip.codec.xml.serializer.model.core.type;

import org.purpleBean.kmip.codec.xml.serializer.kmip.AbstractKmipDataTypeXmlSerializer;
import org.purpleBean.kmip.model.core.type.MaximumItems;

public class MaximumItemsXmlSerializer extends AbstractKmipDataTypeXmlSerializer<MaximumItems, Integer> {

    public MaximumItemsXmlSerializer() {
        super(MaximumItems::getValue);
    }
}