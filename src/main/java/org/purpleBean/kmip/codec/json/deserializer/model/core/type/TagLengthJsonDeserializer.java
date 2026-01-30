package org.purpleBean.kmip.codec.json.deserializer.model.core.type;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import org.purpleBean.kmip.codec.json.deserializer.api.AbstractKmipDataTypeJsonDeserializer;
import org.purpleBean.kmip.model.core.type.TagLength;

import java.io.IOException;

public class TagLengthJsonDeserializer extends AbstractKmipDataTypeJsonDeserializer<TagLength, TagLength.TagLengthBuilder> {

    public TagLengthJsonDeserializer() {
        super(TagLength.kmipTag, TagLength.encodingType);
    }

    @Override
    protected TagLength.TagLengthBuilder createBuilder() {
        return TagLength.builder();
    }

    @Override
    protected void setValue(TagLength.TagLengthBuilder builder, String tag, String type, JsonParser p, DeserializationContext ctxt) throws IOException {
        builder.value(ctxt.readValue(p, Integer.class));
    }

    @Override
    protected TagLength build(TagLength.TagLengthBuilder builder) {
        return builder.build();
    }
}
