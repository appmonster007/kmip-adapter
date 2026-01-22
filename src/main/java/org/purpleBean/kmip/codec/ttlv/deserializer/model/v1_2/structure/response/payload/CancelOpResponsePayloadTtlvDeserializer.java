package org.purpleBean.kmip.codec.ttlv.deserializer.model.v1_2.structure.response.payload;

import org.purpleBean.kmip.api.EncodingType;
import org.purpleBean.kmip.api.KmipTag;
import org.purpleBean.kmip.codec.ttlv.deserializer.api.AbstractKmipStructureTtlvDeserializer;
import org.purpleBean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purpleBean.kmip.model.core.enumeration.CancellationResult;
import org.purpleBean.kmip.model.core.type.AsynchronousCorrelationValue;
import org.purpleBean.kmip.model.v1_2.structure.response.payload.CancelOpResponsePayload;

import java.io.IOException;
import java.nio.ByteBuffer;

public class CancelOpResponsePayloadTtlvDeserializer extends AbstractKmipStructureTtlvDeserializer<CancelOpResponsePayload, CancelOpResponsePayload.CancelOpResponsePayloadBuilder> {

    public CancelOpResponsePayloadTtlvDeserializer() {
        super(CancelOpResponsePayload.kmipTag);
    }

    @Override
    protected CancelOpResponsePayload.CancelOpResponsePayloadBuilder createBuilder() {
        return CancelOpResponsePayload.builder();
    }

    @Override
    protected void setValue(CancelOpResponsePayload.CancelOpResponsePayloadBuilder builder, KmipTag.Value nodeTag, ByteBuffer p, TtlvMapper mapper) throws IOException {
        switch (nodeTag) {
            case KmipTag.Standard.ASYNCHRONOUS_CORRELATION_VALUE ->
                    builder.asynchronousCorrelationValue(mapper.readValue(p, AsynchronousCorrelationValue.class));
            case KmipTag.Standard.CANCELLATION_RESULT ->
                    builder.cancellationResult(mapper.readValue(p, CancellationResult.class));
            default -> throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
        }
    }

    @Override
    protected CancelOpResponsePayload build(CancelOpResponsePayload.CancelOpResponsePayloadBuilder builder) {
        return builder.build();
    }

    @Override
    protected EncodingType getEncodingType() {
        return CancelOpResponsePayload.encodingType;
    }
}
