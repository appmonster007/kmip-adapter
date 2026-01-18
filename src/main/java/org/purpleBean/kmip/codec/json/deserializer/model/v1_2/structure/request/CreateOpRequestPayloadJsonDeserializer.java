package org.purpleBean.kmip.codec.json.deserializer.model.v1_2.structure.request;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import org.purpleBean.kmip.api.KmipTag;
import org.purpleBean.kmip.codec.json.deserializer.api.AbstractKmipStructureJsonDeserializer;
import org.purpleBean.kmip.model.core.enumeration.ObjectType;
import org.purpleBean.kmip.model.core.structure.TemplateAttribute;
import org.purpleBean.kmip.model.v1_2.structure.request.CreateOpRequestPayload;

import java.io.IOException;

public class CreateOpRequestPayloadJsonDeserializer extends AbstractKmipStructureJsonDeserializer<CreateOpRequestPayload, CreateOpRequestPayload.CreateOpRequestPayloadBuilder> {

    public CreateOpRequestPayloadJsonDeserializer() {
        super(CreateOpRequestPayload.kmipTag, CreateOpRequestPayload.encodingType);
    }

    @Override
    protected CreateOpRequestPayload.CreateOpRequestPayloadBuilder createBuilder() {
        return CreateOpRequestPayload.builder();
    }

    @Override
    protected void setValue(CreateOpRequestPayload.CreateOpRequestPayloadBuilder builder, KmipTag.Value nodeTag, JsonParser p, DeserializationContext ctxt) throws IOException {
        switch (nodeTag) {
            case KmipTag.Standard.OBJECT_TYPE -> builder.objectType(ctxt.readValue(p, ObjectType.class));
            case KmipTag.Standard.TEMPLATE_ATTRIBUTE ->
                    builder.templateAttribute(ctxt.readValue(p, TemplateAttribute.class));
            default -> throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
        }
    }

    @Override
    protected CreateOpRequestPayload build(CreateOpRequestPayload.CreateOpRequestPayloadBuilder builder) {
        return builder.build();
    }
}