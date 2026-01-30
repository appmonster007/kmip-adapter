package org.purpleBean.kmip.codec.json.deserializer.model.v1_2.structure.response.payload;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import org.purpleBean.kmip.api.KmipTag;
import org.purpleBean.kmip.codec.json.deserializer.api.AbstractKmipDataTypeJsonDeserializer;
import org.purpleBean.kmip.model.v1_2.structure.response.payload.PutOpResponsePayload;

import java.io.IOException;

public class PutOpResponsePayloadJsonDeserializer extends AbstractKmipDataTypeJsonDeserializer<PutOpResponsePayload, PutOpResponsePayload.PutOpResponsePayloadBuilder> {

    public PutOpResponsePayloadJsonDeserializer() {
        super(PutOpResponsePayload.kmipTag, PutOpResponsePayload.encodingType);
    }

    @Override
    protected PutOpResponsePayload.PutOpResponsePayloadBuilder createBuilder() {
        return PutOpResponsePayload.builder();
    }

    @Override
    protected void setValue(PutOpResponsePayload.PutOpResponsePayloadBuilder builder, String tag, String type, JsonParser p, DeserializationContext ctxt) throws IOException {
        KmipTag.Value nodeTag = KmipTag.fromName(tag);
// No fields to deserialize
    }

    @Override
    protected PutOpResponsePayload build(PutOpResponsePayload.PutOpResponsePayloadBuilder builder) {
        return builder.build();
    }
}
