package org.purpleBean.kmip.codec.ttlv.serializer.kmip.common;

import org.purpleBean.kmip.codec.ttlv.serializer.AbstractKmipTtlvSerializer;
import org.purpleBean.kmip.common.TagLength;

public class TagLengthTtlvSerializer extends AbstractKmipTtlvSerializer<TagLength, Integer> {

    public TagLengthTtlvSerializer() {
        super(TagLength::getValue);
    }
}