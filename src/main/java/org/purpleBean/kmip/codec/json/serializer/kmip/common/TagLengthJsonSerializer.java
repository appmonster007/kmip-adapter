package org.purpleBean.kmip.codec.json.serializer.kmip.common;

import org.purpleBean.kmip.codec.json.serializer.kmip.AbstractKmipDataTypeJsonSerializer;
import org.purpleBean.kmip.common.TagLength;

public class TagLengthJsonSerializer extends AbstractKmipDataTypeJsonSerializer<TagLength, Integer> {

    public TagLengthJsonSerializer() {
        super(TagLength::getValue);
    }
}