package org.purpleBean.kmip.codec.ttlv.deserializer.model.v1_2.structure.request.payload;

import org.purpleBean.kmip.api.KmipTag;
import org.purpleBean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purpleBean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purpleBean.kmip.model.core.type.AsynchronousCorrelationValue;
import org.purpleBean.kmip.model.v1_2.structure.request.payload.CancelOpRequestPayload;

import java.io.IOException;
import java.nio.ByteBuffer;

public class CancelOpRequestPayloadTtlvDeserializer extends AbstractKmipDataTypeTtlvDeserializer<CancelOpRequestPayload, CancelOpRequestPayload.CancelOpRequestPayloadBuilder> {

    public CancelOpRequestPayloadTtlvDeserializer() {
        super(CancelOpRequestPayload.kmipTag, CancelOpRequestPayload.encodingType);
    }

    @Override
    protected CancelOpRequestPayload.CancelOpRequestPayloadBuilder createBuilder() {
        return CancelOpRequestPayload.builder();
    }

    @Override
    protected void setValue(CancelOpRequestPayload.CancelOpRequestPayloadBuilder builder, byte[] tagBytes, ByteBuffer p, TtlvMapper mapper) throws IOException {
        KmipTag.Value nodeTag = KmipTag.fromBytes(tagBytes);
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
}
