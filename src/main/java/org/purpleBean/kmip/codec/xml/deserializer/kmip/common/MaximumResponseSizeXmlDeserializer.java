package org.purpleBean.kmip.codec.xml.deserializer.kmip.common;

import org.purpleBean.kmip.codec.xml.deserializer.kmip.AbstractKmipDataTypeXmlDeserializer;
import org.purpleBean.kmip.common.MaximumResponseSize;

public class MaximumResponseSizeXmlDeserializer extends AbstractKmipDataTypeXmlDeserializer<MaximumResponseSize, Integer> {

    public MaximumResponseSizeXmlDeserializer() {
        super(MaximumResponseSize.kmipTag, MaximumResponseSize.encodingType, Integer.class, value -> MaximumResponseSize.builder().value(value).build());
    }
}