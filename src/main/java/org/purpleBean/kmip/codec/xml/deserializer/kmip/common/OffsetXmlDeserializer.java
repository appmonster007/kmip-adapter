package org.purpleBean.kmip.codec.xml.deserializer.kmip.common;

import org.purpleBean.kmip.codec.xml.deserializer.AbstractKmipXmlDeserializer;
import org.purpleBean.kmip.common.Offset;

public class OffsetXmlDeserializer extends AbstractKmipXmlDeserializer<Offset, Integer> {

    public OffsetXmlDeserializer() {
        super(Offset.kmipTag, Offset.encodingType, Integer.class, value -> Offset.builder().value(value).build());
    }
}