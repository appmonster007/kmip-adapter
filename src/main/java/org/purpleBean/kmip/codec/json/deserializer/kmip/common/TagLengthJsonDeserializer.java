package org.purpleBean.kmip.codec.json.deserializer.kmip.common;

import org.purpleBean.kmip.codec.json.deserializer.AbstractKmipJsonDeserializer;
import org.purpleBean.kmip.common.TagLength;

public class TagLengthJsonDeserializer extends AbstractKmipJsonDeserializer<TagLength, Integer> {

    public TagLengthJsonDeserializer() {
        super(TagLength.kmipTag, TagLength.encodingType, Integer.class, value -> TagLength.builder().value(value).build());
    }
}