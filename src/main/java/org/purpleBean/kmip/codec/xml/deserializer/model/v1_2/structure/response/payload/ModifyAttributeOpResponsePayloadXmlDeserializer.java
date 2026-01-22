package org.purpleBean.kmip.codec.xml.deserializer.model.v1_2.structure.response.payload;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import org.purpleBean.kmip.api.KmipTag;
import org.purpleBean.kmip.codec.xml.deserializer.api.AbstractKmipStructureXmlDeserializer;
import org.purpleBean.kmip.model.core.structure.Attribute;
import org.purpleBean.kmip.model.core.type.UniqueIdentifier;
import org.purpleBean.kmip.model.v1_2.structure.response.payload.ModifyAttributeOpResponsePayload;

import java.io.IOException;

public class ModifyAttributeOpResponsePayloadXmlDeserializer extends AbstractKmipStructureXmlDeserializer<ModifyAttributeOpResponsePayload, ModifyAttributeOpResponsePayload.ModifyAttributeOpResponsePayloadBuilder> {

    public ModifyAttributeOpResponsePayloadXmlDeserializer() {
        super(ModifyAttributeOpResponsePayload.kmipTag);
    }

    @Override
    protected ModifyAttributeOpResponsePayload.ModifyAttributeOpResponsePayloadBuilder createBuilder() {
        return ModifyAttributeOpResponsePayload.builder();
    }

    @Override
    protected void setValue(ModifyAttributeOpResponsePayload.ModifyAttributeOpResponsePayloadBuilder builder, KmipTag.Value nodeTag, JsonParser p, DeserializationContext ctxt) throws IOException {
        switch (nodeTag) {
            case KmipTag.Standard.UNIQUE_IDENTIFIER ->
                    builder.uniqueIdentifier(ctxt.readValue(p, UniqueIdentifier.class));
            case KmipTag.Standard.ATTRIBUTE -> builder.attribute(ctxt.readValue(p, Attribute.class));
            default -> throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
        }
    }

    @Override
    protected ModifyAttributeOpResponsePayload build(ModifyAttributeOpResponsePayload.ModifyAttributeOpResponsePayloadBuilder builder) {
        return builder.build();
    }
}
