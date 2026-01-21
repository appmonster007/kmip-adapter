package org.purpleBean.kmip.codec.json.deserializer.model.v1_2.structure.response.payload;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import org.purpleBean.kmip.api.KmipTag;
import org.purpleBean.kmip.codec.json.deserializer.api.AbstractKmipStructureJsonDeserializer;
import org.purpleBean.kmip.model.core.structure.TemplateAttribute;
import org.purpleBean.kmip.model.core.type.UniqueIdentifier;
import org.purpleBean.kmip.model.v1_2.structure.response.payload.RecertifyOpResponsePayload;

import java.io.IOException;

public class RecertifyOpResponsePayloadJsonDeserializer extends AbstractKmipStructureJsonDeserializer<RecertifyOpResponsePayload, RecertifyOpResponsePayload.RecertifyOpResponsePayloadBuilder> {

    public RecertifyOpResponsePayloadJsonDeserializer() {
        super(RecertifyOpResponsePayload.kmipTag, RecertifyOpResponsePayload.encodingType);
    }

    @Override
    protected RecertifyOpResponsePayload.RecertifyOpResponsePayloadBuilder createBuilder() {
        return RecertifyOpResponsePayload.builder();
    }

    @Override
    protected void setValue(RecertifyOpResponsePayload.RecertifyOpResponsePayloadBuilder builder, KmipTag.Value nodeTag, JsonParser p, DeserializationContext ctxt) throws IOException {
        switch (nodeTag) {
            case KmipTag.Standard.UNIQUE_IDENTIFIER ->
                    builder.uniqueIdentifier(ctxt.readValue(p, UniqueIdentifier.class));
            case KmipTag.Standard.TEMPLATE_ATTRIBUTE ->
                    builder.templateAttribute(ctxt.readValue(p, TemplateAttribute.class));
            default -> throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
        }
    }

    @Override
    protected RecertifyOpResponsePayload build(RecertifyOpResponsePayload.RecertifyOpResponsePayloadBuilder builder) {
        return builder.build();
    }
}