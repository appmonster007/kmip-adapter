package org.purpleBean.kmip.codec.xml.serializer.kmip.common;

import org.purpleBean.kmip.codec.xml.serializer.kmip.AbstractKmipDataTypeXmlSerializer;
import org.purpleBean.kmip.common.MaximumResponseSize;

public class MaximumResponseSizeXmlSerializer extends AbstractKmipDataTypeXmlSerializer<MaximumResponseSize, Integer> {

    public MaximumResponseSizeXmlSerializer() {
        super(MaximumResponseSize::getValue);
    }
}