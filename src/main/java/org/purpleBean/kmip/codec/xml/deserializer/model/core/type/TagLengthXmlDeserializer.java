package org.purpleBean.kmip.codec.xml.deserializer.model.core.type;

import org.purpleBean.kmip.codec.xml.deserializer.api.AbstractKmipDataTypeXmlDeserializer;
import org.purpleBean.kmip.model.core.type.TagLength;

public class TagLengthXmlDeserializer extends AbstractKmipDataTypeXmlDeserializer<TagLength, Integer> {

    public TagLengthXmlDeserializer() {
        super(TagLength.kmipTag, TagLength.encodingType, Integer.class, value -> TagLength.builder().value(value).build());
    }
}