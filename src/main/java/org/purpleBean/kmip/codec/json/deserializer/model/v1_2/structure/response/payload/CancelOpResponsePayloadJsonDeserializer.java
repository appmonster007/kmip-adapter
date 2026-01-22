package org.purpleBean.kmip.codec.json.deserializer.model.v1_2.structure.response.payload;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import org.purpleBean.kmip.api.KmipTag;
import org.purpleBean.kmip.codec.json.deserializer.api.AbstractKmipStructureJsonDeserializer;
import org.purpleBean.kmip.model.core.enumeration.CancellationResult;
import org.purpleBean.kmip.model.core.type.AsynchronousCorrelationValue;
import org.purpleBean.kmip.model.v1_2.structure.response.payload.CancelOpResponsePayload;

import java.io.IOException;

public class CancelOpResponsePayloadJsonDeserializer extends AbstractKmipStructureJsonDeserializer<CancelOpResponsePayload, CancelOpResponsePayload.CancelOpResponsePayloadBuilder> {

    public CancelOpResponsePayloadJsonDeserializer() {
        super(CancelOpResponsePayload.kmipTag, CancelOpResponsePayload.encodingType);
    }

    @Override
    protected CancelOpResponsePayload.CancelOpResponsePayloadBuilder createBuilder() {
        return CancelOpResponsePayload.builder();
    }

    @Override
    protected void setValue(CancelOpResponsePayload.CancelOpResponsePayloadBuilder builder, KmipTag.Value nodeTag, JsonParser p, DeserializationContext ctxt) throws IOException {
        switch (nodeTag) {
            case KmipTag.Standard.ASYNCHRONOUS_CORRELATION_VALUE ->
                    builder.asynchronousCorrelationValue(ctxt.readValue(p, AsynchronousCorrelationValue.class));
            case KmipTag.Standard.CANCELLATION_RESULT ->
                    builder.cancellationResult(ctxt.readValue(p, CancellationResult.class));
            default -> throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
        }
    }

    @Override
    protected CancelOpResponsePayload build(CancelOpResponsePayload.CancelOpResponsePayloadBuilder builder) {
        return builder.build();
    }
}
