package org.purpleBean.kmip.codec.ttlv.deserializer.kmip.common;

import org.purpleBean.kmip.codec.ttlv.deserializer.AbstractKmipTtlvDeserializer;
import org.purpleBean.kmip.common.TagLength;

public class TagLengthTtlvDeserializer extends AbstractKmipTtlvDeserializer<TagLength, Integer> {

    public TagLengthTtlvDeserializer() {
        super(TagLength.kmipTag, TagLength.encodingType, Integer.class, value -> TagLength.builder().value(value).build());
    }
}