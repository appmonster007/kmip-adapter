package org.purpleBean.kmip.codec.ttlv.serializer.kmip.common;

import org.purpleBean.kmip.codec.ttlv.serializer.kmip.AbstractKmipDataTypeTtlvSerializer;
import org.purpleBean.kmip.common.TagLength;

public class TagLengthTtlvSerializer extends AbstractKmipDataTypeTtlvSerializer<TagLength, Integer> {

    public TagLengthTtlvSerializer() {
        super(TagLength::getValue);
    }
}