package org.purpleBean.kmip.codec.ttlv.serializer.model.core.type;

import org.purpleBean.kmip.codec.ttlv.serializer.kmip.AbstractKmipDataTypeTtlvSerializer;
import org.purpleBean.kmip.model.core.type.TagLength;

public class TagLengthTtlvSerializer extends AbstractKmipDataTypeTtlvSerializer<TagLength, Integer> {

    public TagLengthTtlvSerializer() {
        super(TagLength::getValue);
    }
}