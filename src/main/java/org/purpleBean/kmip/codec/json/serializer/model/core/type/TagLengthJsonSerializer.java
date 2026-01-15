package org.purpleBean.kmip.codec.json.serializer.model.core.type;

import org.purpleBean.kmip.codec.json.serializer.kmip.AbstractKmipDataTypeJsonSerializer;
import org.purpleBean.kmip.model.core.type.TagLength;

public class TagLengthJsonSerializer extends AbstractKmipDataTypeJsonSerializer<TagLength, Integer> {

    public TagLengthJsonSerializer() {
        super(TagLength::getValue);
    }
}