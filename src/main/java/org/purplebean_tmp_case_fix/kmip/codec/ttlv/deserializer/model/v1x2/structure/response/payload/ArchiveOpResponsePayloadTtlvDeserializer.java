package org.purplebean.kmip.codec.ttlv.deserializer.model.v1x2.structure.response.payload;

import org.purplebean.kmip.api.KmipTag;
import org.purplebean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purplebean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purplebean.kmip.model.core.type.UniqueIdentifier;
import org.purplebean.kmip.model.v1x2.structure.response.payload.ArchiveOpResponsePayload;

import java.io.IOException;
import java.nio.ByteBuffer;

public class ArchiveOpResponsePayloadTtlvDeserializer extends AbstractKmipDataTypeTtlvDeserializer<ArchiveOpResponsePayload, ArchiveOpResponsePayload.ArchiveOpResponsePayloadBuilder> {

    public ArchiveOpResponsePayloadTtlvDeserializer() {
        super(ArchiveOpResponsePayload.kmipTag, ArchiveOpResponsePayload.encodingType);
    }

    @Override
    protected ArchiveOpResponsePayload.ArchiveOpResponsePayloadBuilder createBuilder() {
        return ArchiveOpResponsePayload.builder();
    }

    @Override
    protected void setValue(ArchiveOpResponsePayload.ArchiveOpResponsePayloadBuilder builder, byte[] tag, byte type, ByteBuffer p, TtlvMapper mapper) throws IOException {
        KmipTag.Value nodeTag = KmipTag.fromBytes(tag);
        if (nodeTag.equals(KmipTag.Standard.UNIQUE_IDENTIFIER)) {
            builder.uniqueIdentifier(mapper.readValue(p, UniqueIdentifier.class));
        } else {
            throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
        }
    }

    @Override
    protected ArchiveOpResponsePayload build(ArchiveOpResponsePayload.ArchiveOpResponsePayloadBuilder builder) {
        return builder.build();
    }
}
