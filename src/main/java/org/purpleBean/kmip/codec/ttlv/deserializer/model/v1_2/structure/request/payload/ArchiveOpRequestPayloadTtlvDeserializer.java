package org.purpleBean.kmip.codec.ttlv.deserializer.model.v1_2.structure.request.payload;

import org.purpleBean.kmip.api.KmipTag;
import org.purpleBean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purpleBean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purpleBean.kmip.model.core.type.UniqueIdentifier;
import org.purpleBean.kmip.model.v1_2.structure.request.payload.ArchiveOpRequestPayload;

import java.io.IOException;
import java.nio.ByteBuffer;

public class ArchiveOpRequestPayloadTtlvDeserializer extends AbstractKmipDataTypeTtlvDeserializer<ArchiveOpRequestPayload, ArchiveOpRequestPayload.ArchiveOpRequestPayloadBuilder> {

    public ArchiveOpRequestPayloadTtlvDeserializer() {
        super(ArchiveOpRequestPayload.kmipTag, ArchiveOpRequestPayload.encodingType);
    }

    @Override
    protected ArchiveOpRequestPayload.ArchiveOpRequestPayloadBuilder createBuilder() {
        return ArchiveOpRequestPayload.builder();
    }

    @Override
    protected void setValue(ArchiveOpRequestPayload.ArchiveOpRequestPayloadBuilder builder, byte[] tagBytes, byte type, ByteBuffer p, TtlvMapper mapper) throws IOException {
        KmipTag.Value nodeTag = KmipTag.fromBytes(tagBytes);
        if (nodeTag.equals(KmipTag.Standard.UNIQUE_IDENTIFIER)) {
            builder.uniqueIdentifier(mapper.readValue(p, UniqueIdentifier.class));
        } else {
            throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
        }
    }

    @Override
    protected ArchiveOpRequestPayload build(ArchiveOpRequestPayload.ArchiveOpRequestPayloadBuilder builder) {
        return builder.build();
    }
}
