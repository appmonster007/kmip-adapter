package org.purpleBean.kmip.codec.json.deserializer.model.v2_1.type;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import org.purpleBean.kmip.codec.json.deserializer.api.AbstractKmipDataTypeJsonDeserializer;
import org.purpleBean.kmip.model.v2_1.type.AttributeReferenceTag;

import java.io.IOException;

public class AttributeReferenceTagJsonDeserializer extends AbstractKmipDataTypeJsonDeserializer<AttributeReferenceTag, AttributeReferenceTag.AttributeReferenceTagBuilder> {

    public AttributeReferenceTagJsonDeserializer() {
        super(AttributeReferenceTag.kmipTag, AttributeReferenceTag.encodingType);
    }

    @Override
    protected AttributeReferenceTag.AttributeReferenceTagBuilder createBuilder() {
        return AttributeReferenceTag.builder();
    }

    @Override
    protected void setValue(AttributeReferenceTag.AttributeReferenceTagBuilder builder, String tag, String type, JsonParser p, DeserializationContext ctxt) throws IOException {
        builder.tagDescription(ctxt.readValue(p, String.class));
    }

    @Override
    protected AttributeReferenceTag build(AttributeReferenceTag.AttributeReferenceTagBuilder builder) {
        return builder.build();
    }
}
