package org.purpleBean.kmip.codec.ttlv.deserializer.model.v1_2.structure.response.payload;

import org.purpleBean.kmip.api.KmipTag;
import org.purpleBean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purpleBean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purpleBean.kmip.model.core.type.UniqueIdentifier;
import org.purpleBean.kmip.model.v1_2.structure.response.payload.ArchiveOpResponsePayload;

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
    protected void setValue(ArchiveOpResponsePayload.ArchiveOpResponsePayloadBuilder builder, byte[] tagBytes, ByteBuffer p, TtlvMapper mapper) throws IOException {
        KmipTag.Value nodeTag = KmipTag.fromBytes(tagBytes);
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
