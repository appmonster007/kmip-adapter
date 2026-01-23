package org.purpleBean.kmip.codec.ttlv.deserializer.model.v1_2.structure.response.payload;

import org.purpleBean.kmip.api.EncodingType;
import org.purpleBean.kmip.api.KmipTag;
import org.purpleBean.kmip.codec.ttlv.deserializer.api.AbstractKmipStructureTtlvDeserializer;
import org.purpleBean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purpleBean.kmip.model.v1_2.structure.response.payload.PutOpResponsePayload;

import java.io.IOException;
import java.nio.ByteBuffer;

public class PutOpResponsePayloadTtlvDeserializer extends AbstractKmipStructureTtlvDeserializer<PutOpResponsePayload, PutOpResponsePayload.PutOpResponsePayloadBuilder> {

    public PutOpResponsePayloadTtlvDeserializer() {
        super(PutOpResponsePayload.kmipTag);
    }

    @Override
    protected PutOpResponsePayload.PutOpResponsePayloadBuilder createBuilder() {
        return PutOpResponsePayload.builder();
    }

    @Override
    protected void setValue(PutOpResponsePayload.PutOpResponsePayloadBuilder builder, KmipTag.Value nodeTag, ByteBuffer p, TtlvMapper mapper) throws IOException {
        // No fields to deserialize
    }

    @Override
    protected PutOpResponsePayload build(PutOpResponsePayload.PutOpResponsePayloadBuilder builder) {
        return builder.build();
    }

    @Override
    protected EncodingType getEncodingType() {
        return PutOpResponsePayload.encodingType;
    }
}
