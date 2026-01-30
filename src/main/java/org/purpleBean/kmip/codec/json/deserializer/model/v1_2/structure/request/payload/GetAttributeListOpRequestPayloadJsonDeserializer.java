package org.purpleBean.kmip.codec.json.deserializer.model.v1_2.structure.request.payload;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import org.purpleBean.kmip.api.KmipTag;
import org.purpleBean.kmip.codec.json.deserializer.api.AbstractKmipDataTypeJsonDeserializer;
import org.purpleBean.kmip.model.core.type.UniqueIdentifier;
import org.purpleBean.kmip.model.v1_2.structure.request.payload.GetAttributeListOpRequestPayload;

import java.io.IOException;

public class GetAttributeListOpRequestPayloadJsonDeserializer extends AbstractKmipDataTypeJsonDeserializer<GetAttributeListOpRequestPayload, GetAttributeListOpRequestPayload.GetAttributeListOpRequestPayloadBuilder> {

    public GetAttributeListOpRequestPayloadJsonDeserializer() {
        super(GetAttributeListOpRequestPayload.kmipTag, GetAttributeListOpRequestPayload.encodingType);
    }

    @Override
    protected GetAttributeListOpRequestPayload.GetAttributeListOpRequestPayloadBuilder createBuilder() {
        return GetAttributeListOpRequestPayload.builder();
    }

    @Override
    protected void setValue(GetAttributeListOpRequestPayload.GetAttributeListOpRequestPayloadBuilder builder, String tag, String type, JsonParser p, DeserializationContext ctxt) throws IOException {
        KmipTag.Value nodeTag = KmipTag.fromName(tag);
        if (nodeTag.equals(KmipTag.Standard.UNIQUE_IDENTIFIER)) {
            builder.uniqueIdentifier(ctxt.readValue(p, UniqueIdentifier.class));
        } else {
            throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
        }
    }

    @Override
    protected GetAttributeListOpRequestPayload build(GetAttributeListOpRequestPayload.GetAttributeListOpRequestPayloadBuilder builder) {
        return builder.build();
    }
}
