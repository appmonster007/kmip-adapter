package org.purpleBean.kmip.codec.ttlv.deserializer.model.v2_1.structure.response.payload;

import org.purpleBean.kmip.api.KmipTag;
import org.purpleBean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purpleBean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purpleBean.kmip.model.core.type.DataByteString;
import org.purpleBean.kmip.model.v2_1.structure.response.payload.HashOpResponsePayload;
import org.purpleBean.kmip.model.v2_1.type.CorrelationValue;

import java.io.IOException;
import java.nio.ByteBuffer;

public class HashOpResponsePayloadTtlvDeserializer extends AbstractKmipDataTypeTtlvDeserializer<HashOpResponsePayload, HashOpResponsePayload.HashOpResponsePayloadBuilder> {

    public HashOpResponsePayloadTtlvDeserializer() {
        super(HashOpResponsePayload.kmipTag, HashOpResponsePayload.encodingType);
    }

    @Override
    protected HashOpResponsePayload.HashOpResponsePayloadBuilder createBuilder() {
        return HashOpResponsePayload.builder();
    }

    @Override
    protected void setValue(HashOpResponsePayload.HashOpResponsePayloadBuilder builder, byte[] tag, byte type, ByteBuffer p, TtlvMapper mapper) throws IOException {
        KmipTag.Value nodeTag = KmipTag.fromBytes(tag);
        switch (nodeTag) {
            case KmipTag.Standard.DATA -> builder.data(mapper.readValue(p, DataByteString.class));
            case KmipTag.Standard.CORRELATION_VALUE -> builder.correlationValue(mapper.readValue(p, CorrelationValue.class));
            default -> throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
        }
    }

    @Override
    protected HashOpResponsePayload build(HashOpResponsePayload.HashOpResponsePayloadBuilder builder) {
        return builder.build();
    }
}
