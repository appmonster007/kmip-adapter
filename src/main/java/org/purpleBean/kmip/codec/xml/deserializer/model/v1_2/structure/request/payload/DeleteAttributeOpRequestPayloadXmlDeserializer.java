package org.purpleBean.kmip.codec.xml.deserializer.model.v1_2.structure.request.payload;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import org.purpleBean.kmip.api.KmipTag;
import org.purpleBean.kmip.codec.xml.deserializer.api.AbstractKmipStructureXmlDeserializer;
import org.purpleBean.kmip.model.core.type.AttributeIndex;
import org.purpleBean.kmip.model.core.type.AttributeName;
import org.purpleBean.kmip.model.core.type.UniqueIdentifier;
import org.purpleBean.kmip.model.v1_2.structure.request.payload.DeleteAttributeOpRequestPayload;

import java.io.IOException;

public class DeleteAttributeOpRequestPayloadXmlDeserializer extends AbstractKmipStructureXmlDeserializer<DeleteAttributeOpRequestPayload, DeleteAttributeOpRequestPayload.DeleteAttributeOpRequestPayloadBuilder> {

    public DeleteAttributeOpRequestPayloadXmlDeserializer() {
        super(DeleteAttributeOpRequestPayload.kmipTag);
    }

    @Override
    protected DeleteAttributeOpRequestPayload.DeleteAttributeOpRequestPayloadBuilder createBuilder() {
        return DeleteAttributeOpRequestPayload.builder();
    }

    @Override
    protected void setValue(DeleteAttributeOpRequestPayload.DeleteAttributeOpRequestPayloadBuilder builder, KmipTag.Value nodeTag, JsonParser p, DeserializationContext ctxt) throws IOException {
        switch (nodeTag) {
            case KmipTag.Standard.UNIQUE_IDENTIFIER ->
                    builder.uniqueIdentifier(ctxt.readValue(p, UniqueIdentifier.class));
            case KmipTag.Standard.ATTRIBUTE_NAME -> builder.attributeName(ctxt.readValue(p, AttributeName.class));
            case KmipTag.Standard.ATTRIBUTE_INDEX -> builder.attributeIndex(ctxt.readValue(p, AttributeIndex.class));
            default -> throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
        }
    }

    @Override
    protected DeleteAttributeOpRequestPayload build(DeleteAttributeOpRequestPayload.DeleteAttributeOpRequestPayloadBuilder builder) {
        return builder.build();
    }
}
