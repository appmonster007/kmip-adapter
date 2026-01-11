package org.purpleBean.kmip.codec.xml.deserializer.kmip.common;

import org.purpleBean.kmip.codec.xml.deserializer.AbstractKmipXmlDeserializer;
import org.purpleBean.kmip.common.TagLength;

public class TagLengthXmlDeserializer extends AbstractKmipXmlDeserializer<TagLength, Integer> {

    public TagLengthXmlDeserializer() {
        super(TagLength.kmipTag, TagLength.encodingType, Integer.class, value -> TagLength.builder().value(value).build());
    }
}