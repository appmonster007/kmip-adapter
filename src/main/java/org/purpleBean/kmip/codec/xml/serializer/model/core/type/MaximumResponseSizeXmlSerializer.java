package org.purpleBean.kmip.codec.xml.serializer.model.core.type;

import org.purpleBean.kmip.codec.xml.serializer.kmip.AbstractKmipDataTypeXmlSerializer;
import org.purpleBean.kmip.model.core.type.MaximumResponseSize;

public class MaximumResponseSizeXmlSerializer extends AbstractKmipDataTypeXmlSerializer<MaximumResponseSize, Integer> {

    public MaximumResponseSizeXmlSerializer() {
        super(MaximumResponseSize::getValue);
    }
}