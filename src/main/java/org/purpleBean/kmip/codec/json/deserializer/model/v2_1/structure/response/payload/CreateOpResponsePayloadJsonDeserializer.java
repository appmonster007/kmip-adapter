package org.purpleBean.kmip.codec.json.deserializer.model.v2_1.structure.response.payload;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import org.purpleBean.kmip.api.KmipTag;
import org.purpleBean.kmip.codec.json.deserializer.api.AbstractKmipDataTypeJsonDeserializer;
import org.purpleBean.kmip.model.core.enumeration.ObjectType;
import org.purpleBean.kmip.model.core.type.UniqueIdentifier;
import org.purpleBean.kmip.model.v2_1.structure.response.payload.CreateOpResponsePayload;

import java.io.IOException;

public class CreateOpResponsePayloadJsonDeserializer extends AbstractKmipDataTypeJsonDeserializer<CreateOpResponsePayload, CreateOpResponsePayload.CreateOpResponsePayloadBuilder> {

    public CreateOpResponsePayloadJsonDeserializer() {
        super(CreateOpResponsePayload.kmipTag, CreateOpResponsePayload.encodingType);
    }

    @Override
    protected CreateOpResponsePayload.CreateOpResponsePayloadBuilder createBuilder() {
        return CreateOpResponsePayload.builder();
    }

    @Override
    protected void setValue(CreateOpResponsePayload.CreateOpResponsePayloadBuilder builder, String tag, String type, JsonParser p, DeserializationContext ctxt) throws IOException {
        KmipTag.Value nodeTag = KmipTag.fromName(tag);
        switch (nodeTag) {
            case KmipTag.Standard.OBJECT_TYPE -> builder.objectType(ctxt.readValue(p, ObjectType.class));
            case KmipTag.Standard.UNIQUE_IDENTIFIER -> builder.uniqueIdentifier(ctxt.readValue(p, UniqueIdentifier.class));
            default -> throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
        }
    }

    @Override
    protected CreateOpResponsePayload build(CreateOpResponsePayload.CreateOpResponsePayloadBuilder builder) {
        return builder.build();
    }
}
