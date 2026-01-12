package org.purpleBean.kmip.codec.xml.serializer.kmip.common;

import org.purpleBean.kmip.common.MaximumResponseSize;
import org.purpleBean.kmip.codec.xml.serializer.AbstractKmipXmlSerializer;

public class MaximumResponseSizeXmlSerializer extends AbstractKmipXmlSerializer<MaximumResponseSize, Integer> {

    public MaximumResponseSizeXmlSerializer() {
        super(MaximumResponseSize::getValue);
    }
}