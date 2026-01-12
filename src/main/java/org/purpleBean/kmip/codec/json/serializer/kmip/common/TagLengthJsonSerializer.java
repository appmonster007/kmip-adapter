package org.purpleBean.kmip.codec.json.serializer.kmip.common;

import org.purpleBean.kmip.codec.json.serializer.AbstractKmipJsonSerializer;
import org.purpleBean.kmip.common.TagLength;

public class TagLengthJsonSerializer extends AbstractKmipJsonSerializer<TagLength, Integer> {

    public TagLengthJsonSerializer() {
        super(TagLength::getValue);
    }
}