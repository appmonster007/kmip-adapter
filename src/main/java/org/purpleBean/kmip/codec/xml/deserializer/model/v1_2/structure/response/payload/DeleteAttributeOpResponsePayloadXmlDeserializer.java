package org.purpleBean.kmip.codec.xml.deserializer.model.v1_2.structure.response.payload;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import org.purpleBean.kmip.api.KmipTag;
import org.purpleBean.kmip.codec.xml.deserializer.api.AbstractKmipStructureXmlDeserializer;
import org.purpleBean.kmip.model.core.structure.Attribute;
import org.purpleBean.kmip.model.core.type.UniqueIdentifier;
import org.purpleBean.kmip.model.v1_2.structure.response.payload.DeleteAttributeOpResponsePayload;

import java.io.IOException;

public class DeleteAttributeOpResponsePayloadXmlDeserializer extends AbstractKmipStructureXmlDeserializer<DeleteAttributeOpResponsePayload, DeleteAttributeOpResponsePayload.DeleteAttributeOpResponsePayloadBuilder> {

    public DeleteAttributeOpResponsePayloadXmlDeserializer() {
        super(DeleteAttributeOpResponsePayload.kmipTag);
    }

    @Override
    protected DeleteAttributeOpResponsePayload.DeleteAttributeOpResponsePayloadBuilder createBuilder() {
        return DeleteAttributeOpResponsePayload.builder();
    }

    @Override
    protected void setValue(DeleteAttributeOpResponsePayload.DeleteAttributeOpResponsePayloadBuilder builder, KmipTag.Value nodeTag, JsonParser p, DeserializationContext ctxt) throws IOException {
        switch (nodeTag) {
            case KmipTag.Standard.UNIQUE_IDENTIFIER ->
                    builder.uniqueIdentifier(ctxt.readValue(p, UniqueIdentifier.class));
            case KmipTag.Standard.ATTRIBUTE -> builder.attribute(ctxt.readValue(p, Attribute.class));
            default -> throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
        }
    }

    @Override
    protected DeleteAttributeOpResponsePayload build(DeleteAttributeOpResponsePayload.DeleteAttributeOpResponsePayloadBuilder builder) {
        return builder.build();
    }
}
