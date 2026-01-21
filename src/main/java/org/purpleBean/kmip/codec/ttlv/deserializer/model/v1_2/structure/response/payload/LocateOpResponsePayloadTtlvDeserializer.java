package org.purpleBean.kmip.codec.ttlv.deserializer.model.v1_2.structure.response.payload;

import org.purpleBean.kmip.api.EncodingType;
import org.purpleBean.kmip.api.KmipTag;
import org.purpleBean.kmip.codec.ttlv.deserializer.api.AbstractKmipStructureTtlvDeserializer;
import org.purpleBean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purpleBean.kmip.model.core.type.UniqueIdentifier;
import org.purpleBean.kmip.model.v1_2.structure.response.payload.LocateOpResponsePayload;

import java.io.IOException;
import java.nio.ByteBuffer;

public class LocateOpResponsePayloadTtlvDeserializer extends AbstractKmipStructureTtlvDeserializer<LocateOpResponsePayload, LocateOpResponsePayload.LocateOpResponsePayloadBuilder> {

    public LocateOpResponsePayloadTtlvDeserializer() {
        super(LocateOpResponsePayload.kmipTag);
    }

    @Override
    protected LocateOpResponsePayload.LocateOpResponsePayloadBuilder createBuilder() {
        return LocateOpResponsePayload.builder();
    }

    @Override
    protected void setValue(LocateOpResponsePayload.LocateOpResponsePayloadBuilder builder, KmipTag.Value nodeTag, ByteBuffer p, TtlvMapper mapper) throws IOException {
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

    @Override
    protected EncodingType getEncodingType() {
        return LocateOpResponsePayload.encodingType;
    }
}
