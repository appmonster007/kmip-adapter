package org.purpleBean.kmip.codec.xml.deserializer.model.v1_2.structure.response.payload;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import org.purpleBean.kmip.api.KmipTag;
import org.purpleBean.kmip.codec.xml.deserializer.api.AbstractKmipStructureXmlDeserializer;
import org.purpleBean.kmip.model.v1_2.structure.response.payload.PutOpResponsePayload;

import java.io.IOException;

public class PutOpResponsePayloadXmlDeserializer extends AbstractKmipStructureXmlDeserializer<PutOpResponsePayload, PutOpResponsePayload.PutOpResponsePayloadBuilder> {

    public PutOpResponsePayloadXmlDeserializer() {
        super(PutOpResponsePayload.kmipTag);
    }

    @Override
    protected PutOpResponsePayload.PutOpResponsePayloadBuilder createBuilder() {
        return PutOpResponsePayload.builder();
    }

    @Override
    protected void setValue(PutOpResponsePayload.PutOpResponsePayloadBuilder builder, KmipTag.Value nodeTag, JsonParser p, DeserializationContext ctxt) throws IOException {
        // No fields to deserialize
    }

    @Override
    protected PutOpResponsePayload build(PutOpResponsePayload.PutOpResponsePayloadBuilder builder) {
        return builder.build();
    }
}
