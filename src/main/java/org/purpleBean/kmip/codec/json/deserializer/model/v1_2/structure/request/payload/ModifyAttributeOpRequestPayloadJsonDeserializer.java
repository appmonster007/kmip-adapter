package org.purpleBean.kmip.codec.json.deserializer.model.v1_2.structure.request.payload;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import org.purpleBean.kmip.api.KmipTag;
import org.purpleBean.kmip.codec.json.deserializer.api.AbstractKmipDataTypeJsonDeserializer;
import org.purpleBean.kmip.model.core.structure.Attribute;
import org.purpleBean.kmip.model.core.type.UniqueIdentifier;
import org.purpleBean.kmip.model.v1_2.structure.request.payload.ModifyAttributeOpRequestPayload;

import java.io.IOException;

public class ModifyAttributeOpRequestPayloadJsonDeserializer extends AbstractKmipDataTypeJsonDeserializer<ModifyAttributeOpRequestPayload, ModifyAttributeOpRequestPayload.ModifyAttributeOpRequestPayloadBuilder> {

    public ModifyAttributeOpRequestPayloadJsonDeserializer() {
        super(ModifyAttributeOpRequestPayload.kmipTag, ModifyAttributeOpRequestPayload.encodingType);
    }

    @Override
    protected ModifyAttributeOpRequestPayload.ModifyAttributeOpRequestPayloadBuilder createBuilder() {
        return ModifyAttributeOpRequestPayload.builder();
    }

    @Override
    protected void setValue(ModifyAttributeOpRequestPayload.ModifyAttributeOpRequestPayloadBuilder builder, String tag, String type, JsonParser p, DeserializationContext ctxt) throws IOException {
        KmipTag.Value nodeTag = KmipTag.fromName(tag);
        switch (nodeTag) {
            case KmipTag.Standard.UNIQUE_IDENTIFIER ->
                    builder.uniqueIdentifier(ctxt.readValue(p, UniqueIdentifier.class));
            case KmipTag.Standard.ATTRIBUTE -> builder.attribute(ctxt.readValue(p, Attribute.class));
            default -> throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
        }
    }

    @Override
    protected ModifyAttributeOpRequestPayload build(ModifyAttributeOpRequestPayload.ModifyAttributeOpRequestPayloadBuilder builder) {
        return builder.build();
    }
}
