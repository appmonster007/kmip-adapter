package org.purpleBean.kmip.codec.json.deserializer.model.v1_2.structure.request.payload;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import org.purpleBean.kmip.api.KmipTag;
import org.purpleBean.kmip.codec.json.deserializer.api.AbstractKmipDataTypeJsonDeserializer;
import org.purpleBean.kmip.model.core.type.AttributeName;
import org.purpleBean.kmip.model.core.type.UniqueIdentifier;
import org.purpleBean.kmip.model.v1_2.structure.request.payload.GetAttributesOpRequestPayload;

import java.io.IOException;

public class GetAttributesOpRequestPayloadJsonDeserializer extends AbstractKmipDataTypeJsonDeserializer<GetAttributesOpRequestPayload, GetAttributesOpRequestPayload.GetAttributesOpRequestPayloadBuilder> {

    public GetAttributesOpRequestPayloadJsonDeserializer() {
        super(GetAttributesOpRequestPayload.kmipTag, GetAttributesOpRequestPayload.encodingType);
    }

    @Override
    protected GetAttributesOpRequestPayload.GetAttributesOpRequestPayloadBuilder createBuilder() {
        return GetAttributesOpRequestPayload.builder();
    }

    @Override
    protected void setValue(GetAttributesOpRequestPayload.GetAttributesOpRequestPayloadBuilder builder, String tag, String type, JsonParser p, DeserializationContext ctxt) throws IOException {
        KmipTag.Value nodeTag = KmipTag.fromName(tag);
        switch (nodeTag) {
            case KmipTag.Standard.UNIQUE_IDENTIFIER ->
                    builder.uniqueIdentifier(ctxt.readValue(p, UniqueIdentifier.class));
            case KmipTag.Standard.ATTRIBUTE_NAME -> builder.attributeName(ctxt.readValue(p, AttributeName.class));
            default -> throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
        }
    }

    @Override
    protected GetAttributesOpRequestPayload build(GetAttributesOpRequestPayload.GetAttributesOpRequestPayloadBuilder builder) {
        return builder.build();
    }
}
