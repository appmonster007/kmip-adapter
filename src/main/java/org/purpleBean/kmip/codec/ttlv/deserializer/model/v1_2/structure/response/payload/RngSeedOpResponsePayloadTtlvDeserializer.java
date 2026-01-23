package org.purpleBean.kmip.codec.ttlv.deserializer.model.v1_2.structure.response.payload;

import org.purpleBean.kmip.api.EncodingType;
import org.purpleBean.kmip.api.KmipTag;
import org.purpleBean.kmip.codec.ttlv.deserializer.api.AbstractKmipStructureTtlvDeserializer;
import org.purpleBean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purpleBean.kmip.model.core.type.DataLength;
import org.purpleBean.kmip.model.v1_2.structure.response.payload.RngSeedOpResponsePayload;

import java.io.IOException;
import java.nio.ByteBuffer;

public class RngSeedOpResponsePayloadTtlvDeserializer extends AbstractKmipStructureTtlvDeserializer<RngSeedOpResponsePayload, RngSeedOpResponsePayload.RngSeedOpResponsePayloadBuilder> {

    public RngSeedOpResponsePayloadTtlvDeserializer() {
        super(RngSeedOpResponsePayload.kmipTag);
    }

    @Override
    protected RngSeedOpResponsePayload.RngSeedOpResponsePayloadBuilder createBuilder() {
        return RngSeedOpResponsePayload.builder();
    }

    @Override
    protected void setValue(RngSeedOpResponsePayload.RngSeedOpResponsePayloadBuilder builder, KmipTag.Value nodeTag, ByteBuffer p, TtlvMapper mapper) throws IOException {
        if (nodeTag.equals(KmipTag.Standard.DATA_LENGTH)) {
            builder.dataLength(mapper.readValue(p, DataLength.class));
        } else {
            throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
        }
    }

    @Override
    protected RngSeedOpResponsePayload build(RngSeedOpResponsePayload.RngSeedOpResponsePayloadBuilder builder) {
        return builder.build();
    }

    @Override
    protected EncodingType getEncodingType() {
        return RngSeedOpResponsePayload.encodingType;
    }
}
