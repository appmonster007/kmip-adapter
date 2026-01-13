package org.purpleBean.kmip.codec.xml.deserializer.kmip.common;

import org.purpleBean.kmip.codec.xml.deserializer.kmip.AbstractKmipDataTypeXmlDeserializer;
import org.purpleBean.kmip.common.Fresh;

public class FreshXmlDeserializer extends AbstractKmipDataTypeXmlDeserializer<Fresh, Boolean> {

    public FreshXmlDeserializer() {
        super(Fresh.kmipTag, Fresh.encodingType, Boolean.class, value -> Fresh.builder().value(value).build());
    }
}