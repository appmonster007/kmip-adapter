package org.purpleBean.kmip.codec.ttlv.deserializer.model.v1_2.structure.response.payload;

import org.purpleBean.kmip.api.KmipTag;
import org.purpleBean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purpleBean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purpleBean.kmip.model.core.type.UniqueIdentifier;
import org.purpleBean.kmip.model.v1_2.structure.response.payload.LocateOpResponsePayload;

import java.io.IOException;
import java.nio.ByteBuffer;

public class LocateOpResponsePayloadTtlvDeserializer extends AbstractKmipDataTypeTtlvDeserializer<LocateOpResponsePayload, LocateOpResponsePayload.LocateOpResponsePayloadBuilder> {

    public LocateOpResponsePayloadTtlvDeserializer() {
        super(LocateOpResponsePayload.kmipTag, LocateOpResponsePayload.encodingType);
    }

    @Override
    protected LocateOpResponsePayload.LocateOpResponsePayloadBuilder createBuilder() {
        return LocateOpResponsePayload.builder();
    }

    @Override
    protected void setValue(LocateOpResponsePayload.LocateOpResponsePayloadBuilder builder, byte[] tag, byte type, ByteBuffer p, TtlvMapper mapper) throws IOException {
        KmipTag.Value nodeTag = KmipTag.fromBytes(tag);
        switch (nodeTag) {
            case KmipTag.Standard.UNIQUE_IDENTIFIER ->
                    builder.uniqueIdentifier(mapper.readValue(p, UniqueIdentifier.class));
            default -> throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
        }
    }

    @Override
    protected LocateOpResponsePayload build(LocateOpResponsePayload.LocateOpResponsePayloadBuilder builder) {
        return builder.build();
    }
}
