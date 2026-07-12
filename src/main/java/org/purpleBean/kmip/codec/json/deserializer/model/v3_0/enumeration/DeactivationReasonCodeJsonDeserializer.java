package org.purpleBean.kmip.codec.json.deserializer.model.v3_0.enumeration;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import org.purpleBean.kmip.codec.json.deserializer.api.AbstractKmipDataTypeJsonDeserializer;
import org.purpleBean.kmip.model.v3_0.enumeration.DeactivationReasonCode;

import java.io.IOException;

public class DeactivationReasonCodeJsonDeserializer extends AbstractKmipDataTypeJsonDeserializer<DeactivationReasonCode, DeactivationReasonCode.DeactivationReasonCodeBuilder> {

    public DeactivationReasonCodeJsonDeserializer() {
        super(DeactivationReasonCode.kmipTag, DeactivationReasonCode.encodingType);
    }

    @Override
    protected DeactivationReasonCode.DeactivationReasonCodeBuilder createBuilder() {
        return DeactivationReasonCode.builder();
    }

    @Override
    protected void setValue(DeactivationReasonCode.DeactivationReasonCodeBuilder builder, String tag, String type, JsonParser p, DeserializationContext ctxt) throws IOException {
        builder.value(DeactivationReasonCode.fromName(ctxt.readValue(p, String.class)));
    }

    @Override
    protected DeactivationReasonCode build(DeactivationReasonCode.DeactivationReasonCodeBuilder builder) {
        return builder.build();
    }
}
