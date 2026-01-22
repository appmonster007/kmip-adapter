package org.purpleBean.kmip.codec.ttlv.deserializer.model.v1_2.structure.request.payload;

import org.purpleBean.kmip.api.EncodingType;
import org.purpleBean.kmip.api.KmipTag;
import org.purpleBean.kmip.codec.ttlv.deserializer.api.AbstractKmipStructureTtlvDeserializer;
import org.purpleBean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purpleBean.kmip.model.core.type.AsynchronousCorrelationValue;
import org.purpleBean.kmip.model.v1_2.structure.request.payload.PollOpRequestPayload;

import java.io.IOException;
import java.nio.ByteBuffer;

public class PollOpRequestPayloadTtlvDeserializer extends AbstractKmipStructureTtlvDeserializer<PollOpRequestPayload, PollOpRequestPayload.PollOpRequestPayloadBuilder> {

    public PollOpRequestPayloadTtlvDeserializer() {
        super(PollOpRequestPayload.kmipTag);
    }

    @Override
    protected PollOpRequestPayload.PollOpRequestPayloadBuilder createBuilder() {
        return PollOpRequestPayload.builder();
    }

    @Override
    protected void setValue(PollOpRequestPayload.PollOpRequestPayloadBuilder builder, KmipTag.Value nodeTag, ByteBuffer p, TtlvMapper mapper) throws IOException {
        if (nodeTag.equals(KmipTag.Standard.ASYNCHRONOUS_CORRELATION_VALUE)) {
            builder.asynchronousCorrelationValue(mapper.readValue(p, AsynchronousCorrelationValue.class));
        } else {
            throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
        }
    }

    @Override
    protected PollOpRequestPayload build(PollOpRequestPayload.PollOpRequestPayloadBuilder builder) {
        return builder.build();
    }

    @Override
    protected EncodingType getEncodingType() {
        return PollOpRequestPayload.encodingType;
    }
}
