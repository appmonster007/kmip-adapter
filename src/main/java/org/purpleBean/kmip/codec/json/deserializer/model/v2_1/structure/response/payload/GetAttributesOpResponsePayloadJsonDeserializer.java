package org.purpleBean.kmip.codec.json.deserializer.model.v2_1.structure.response.payload;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import org.purpleBean.kmip.api.KmipTag;
import org.purpleBean.kmip.codec.json.deserializer.api.AbstractKmipDataTypeJsonDeserializer;
import org.purpleBean.kmip.model.core.type.UniqueIdentifier;
import org.purpleBean.kmip.model.v2_1.structure.Attributes;
import org.purpleBean.kmip.model.v2_1.structure.response.payload.GetAttributesOpResponsePayload;

import java.io.IOException;

public class GetAttributesOpResponsePayloadJsonDeserializer extends AbstractKmipDataTypeJsonDeserializer<GetAttributesOpResponsePayload, GetAttributesOpResponsePayload.GetAttributesOpResponsePayloadBuilder> {

    public GetAttributesOpResponsePayloadJsonDeserializer() {
        super(GetAttributesOpResponsePayload.kmipTag, GetAttributesOpResponsePayload.encodingType);
    }

    @Override
    protected GetAttributesOpResponsePayload.GetAttributesOpResponsePayloadBuilder createBuilder() {
        return GetAttributesOpResponsePayload.builder();
    }

    @Override
    protected void setValue(GetAttributesOpResponsePayload.GetAttributesOpResponsePayloadBuilder builder, String tag, String type, JsonParser p, DeserializationContext ctxt) throws IOException {
        KmipTag.Value nodeTag = KmipTag.fromName(tag);
        switch (nodeTag) {
            case KmipTag.Standard.UNIQUE_IDENTIFIER -> builder.uniqueIdentifier(ctxt.readValue(p, UniqueIdentifier.class));
            case KmipTag.Standard.ATTRIBUTES -> builder.attributes(ctxt.readValue(p, Attributes.class));
            default -> throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
        }
    }

    @Override
    protected GetAttributesOpResponsePayload build(GetAttributesOpResponsePayload.GetAttributesOpResponsePayloadBuilder builder) {
        return builder.build();
    }
}
