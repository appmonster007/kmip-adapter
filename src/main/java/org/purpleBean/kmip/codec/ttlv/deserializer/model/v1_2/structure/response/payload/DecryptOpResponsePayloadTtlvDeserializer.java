package org.purpleBean.kmip.codec.ttlv.deserializer.model.v1_2.structure.response.payload;

import org.purpleBean.kmip.api.KmipTag;
import org.purpleBean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purpleBean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purpleBean.kmip.model.core.type.DataByteString;
import org.purpleBean.kmip.model.core.type.UniqueIdentifier;
import org.purpleBean.kmip.model.v1_2.structure.response.payload.DecryptOpResponsePayload;
import org.purpleBean.kmip.model.v2_1.type.CorrelationValue;

import java.io.IOException;
import java.nio.ByteBuffer;

public class DecryptOpResponsePayloadTtlvDeserializer extends AbstractKmipDataTypeTtlvDeserializer<DecryptOpResponsePayload, DecryptOpResponsePayload.DecryptOpResponsePayloadBuilder> {

    public DecryptOpResponsePayloadTtlvDeserializer() {
        super(DecryptOpResponsePayload.kmipTag, DecryptOpResponsePayload.encodingType);
    }

    @Override
    protected DecryptOpResponsePayload.DecryptOpResponsePayloadBuilder createBuilder() {
        return DecryptOpResponsePayload.builder();
    }

    @Override
    protected void setValue(DecryptOpResponsePayload.DecryptOpResponsePayloadBuilder builder, byte[] tag, byte type, ByteBuffer p, TtlvMapper mapper) throws IOException {
        KmipTag.Value nodeTag = KmipTag.fromBytes(tag);
        switch (nodeTag) {
            case KmipTag.Standard.UNIQUE_IDENTIFIER ->
                    builder.uniqueIdentifier(mapper.readValue(p, UniqueIdentifier.class));
            case KmipTag.Standard.DATA -> builder.data(mapper.readValue(p, DataByteString.class));
            case KmipTag.Standard.CORRELATION_VALUE -> builder.correlationValue(mapper.readValue(p, CorrelationValue.class));
            default -> throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
        }
    }

    @Override
    protected DecryptOpResponsePayload build(DecryptOpResponsePayload.DecryptOpResponsePayloadBuilder builder) {
        return builder.build();
    }
}
