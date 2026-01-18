package org.purpleBean.kmip.codec.xml.deserializer.model.core.type;

import org.purpleBean.kmip.codec.xml.deserializer.api.AbstractKmipDataTypeXmlDeserializer;
import org.purpleBean.kmip.model.core.type.MaximumResponseSize;

public class MaximumResponseSizeXmlDeserializer extends AbstractKmipDataTypeXmlDeserializer<MaximumResponseSize, Integer> {

    public MaximumResponseSizeXmlDeserializer() {
        super(MaximumResponseSize.kmipTag, MaximumResponseSize.encodingType, Integer.class, value -> MaximumResponseSize.builder().value(value).build());
    }
}