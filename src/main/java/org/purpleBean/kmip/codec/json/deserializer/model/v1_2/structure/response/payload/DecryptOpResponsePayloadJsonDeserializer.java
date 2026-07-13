package org.purpleBean.kmip.codec.json.deserializer.model.v1_2.structure.response.payload;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import org.purpleBean.kmip.api.KmipTag;
import org.purpleBean.kmip.codec.json.deserializer.api.AbstractKmipDataTypeJsonDeserializer;
import org.purpleBean.kmip.model.core.type.DataByteString;
import org.purpleBean.kmip.model.core.type.UniqueIdentifier;
import org.purpleBean.kmip.model.v1_2.structure.response.payload.DecryptOpResponsePayload;

import java.io.IOException;

public class DecryptOpResponsePayloadJsonDeserializer extends AbstractKmipDataTypeJsonDeserializer<DecryptOpResponsePayload, DecryptOpResponsePayload.DecryptOpResponsePayloadBuilder> {

    public DecryptOpResponsePayloadJsonDeserializer() {
        super(DecryptOpResponsePayload.kmipTag, DecryptOpResponsePayload.encodingType);
    }

    @Override
    protected DecryptOpResponsePayload.DecryptOpResponsePayloadBuilder createBuilder() {
        return DecryptOpResponsePayload.builder();
    }

    @Override
    protected void setValue(DecryptOpResponsePayload.DecryptOpResponsePayloadBuilder builder, String tag, String type, JsonParser p, DeserializationContext ctxt) throws IOException {
        KmipTag.Value nodeTag = KmipTag.fromName(tag);
        switch (nodeTag) {
            case KmipTag.Standard.UNIQUE_IDENTIFIER ->
                    builder.uniqueIdentifier(ctxt.readValue(p, UniqueIdentifier.class));
            case KmipTag.Standard.DATA -> builder.data(ctxt.readValue(p, DataByteString.class));
            default -> throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
        }
    }

    @Override
    protected DecryptOpResponsePayload build(DecryptOpResponsePayload.DecryptOpResponsePayloadBuilder builder) {
        return builder.build();
    }
}
