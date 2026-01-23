package org.purpleBean.kmip.codec.ttlv.deserializer.model.v1_2.structure.response.payload;

import org.purpleBean.kmip.api.EncodingType;
import org.purpleBean.kmip.api.KmipTag;
import org.purpleBean.kmip.codec.ttlv.deserializer.api.AbstractKmipStructureTtlvDeserializer;
import org.purpleBean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purpleBean.kmip.model.v1_2.structure.response.payload.NotifyOpResponsePayload;

import java.io.IOException;
import java.nio.ByteBuffer;

public class NotifyOpResponsePayloadTtlvDeserializer extends AbstractKmipStructureTtlvDeserializer<NotifyOpResponsePayload, NotifyOpResponsePayload.NotifyOpResponsePayloadBuilder> {

    public NotifyOpResponsePayloadTtlvDeserializer() {
        super(NotifyOpResponsePayload.kmipTag);
    }

    @Override
    protected NotifyOpResponsePayload.NotifyOpResponsePayloadBuilder createBuilder() {
        return NotifyOpResponsePayload.builder();
    }

    @Override
    protected void setValue(NotifyOpResponsePayload.NotifyOpResponsePayloadBuilder builder, KmipTag.Value nodeTag, ByteBuffer p, TtlvMapper mapper) throws IOException {
        // No fields to deserialize
    }

    @Override
    protected NotifyOpResponsePayload build(NotifyOpResponsePayload.NotifyOpResponsePayloadBuilder builder) {
        return builder.build();
    }

    @Override
    protected EncodingType getEncodingType() {
        return NotifyOpResponsePayload.encodingType;
    }
}
