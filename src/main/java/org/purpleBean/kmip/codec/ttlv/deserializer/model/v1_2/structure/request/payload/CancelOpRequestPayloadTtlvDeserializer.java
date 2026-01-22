package org.purpleBean.kmip.codec.ttlv.deserializer.model.v1_2.structure.request.payload;

import org.purpleBean.kmip.api.EncodingType;
import org.purpleBean.kmip.api.KmipTag;
import org.purpleBean.kmip.codec.ttlv.deserializer.api.AbstractKmipStructureTtlvDeserializer;
import org.purpleBean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purpleBean.kmip.model.core.type.AsynchronousCorrelationValue;
import org.purpleBean.kmip.model.v1_2.structure.request.payload.CancelOpRequestPayload;

import java.io.IOException;
import java.nio.ByteBuffer;

public class CancelOpRequestPayloadTtlvDeserializer extends AbstractKmipStructureTtlvDeserializer<CancelOpRequestPayload, CancelOpRequestPayload.CancelOpRequestPayloadBuilder> {

    public CancelOpRequestPayloadTtlvDeserializer() {
        super(CancelOpRequestPayload.kmipTag);
    }

    @Override
    protected CancelOpRequestPayload.CancelOpRequestPayloadBuilder createBuilder() {
        return CancelOpRequestPayload.builder();
    }

    @Override
    protected void setValue(CancelOpRequestPayload.CancelOpRequestPayloadBuilder builder, KmipTag.Value nodeTag, ByteBuffer p, TtlvMapper mapper) throws IOException {
        if (nodeTag.equals(KmipTag.Standard.ASYNCHRONOUS_CORRELATION_VALUE)) {
            builder.asynchronousCorrelationValue(mapper.readValue(p, AsynchronousCorrelationValue.class));
        } else {
            throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
        }
    }

    @Override
    protected CancelOpRequestPayload build(CancelOpRequestPayload.CancelOpRequestPayloadBuilder builder) {
        return builder.build();
    }

    @Override
    protected EncodingType getEncodingType() {
        return CancelOpRequestPayload.encodingType;
    }
}
