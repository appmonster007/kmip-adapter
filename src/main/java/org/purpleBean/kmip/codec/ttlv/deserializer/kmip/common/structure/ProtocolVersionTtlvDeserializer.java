package org.purpleBean.kmip.codec.ttlv.deserializer.kmip.common.structure;

import org.purpleBean.kmip.EncodingType;
import org.purpleBean.kmip.KmipTag;
import org.purpleBean.kmip.codec.ttlv.deserializer.kmip.AbstractKmipStructureTtlvDeserializer;
import org.purpleBean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purpleBean.kmip.common.ProtocolVersionMajor;
import org.purpleBean.kmip.common.ProtocolVersionMinor;
import org.purpleBean.kmip.common.structure.ProtocolVersion;

import java.io.IOException;
import java.nio.ByteBuffer;

public class ProtocolVersionTtlvDeserializer extends AbstractKmipStructureTtlvDeserializer<ProtocolVersion, ProtocolVersion.ProtocolVersionBuilder> {

    public ProtocolVersionTtlvDeserializer() {
        super(ProtocolVersion.kmipTag);
    }

    @Override
    protected ProtocolVersion.ProtocolVersionBuilder createBuilder() {
        return ProtocolVersion.builder();
    }

    @Override
    protected void setValue(ProtocolVersion.ProtocolVersionBuilder builder, KmipTag.Value nodeTag, ByteBuffer p, TtlvMapper mapper) throws IOException {
        switch (nodeTag) {
            case KmipTag.Standard.PROTOCOL_VERSION_MAJOR ->
                    builder.protocolVersionMajor(mapper.readValue(p, ProtocolVersionMajor.class));
            case KmipTag.Standard.PROTOCOL_VERSION_MINOR ->
                    builder.protocolVersionMinor(mapper.readValue(p, ProtocolVersionMinor.class));
            default -> throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
        }
    }

    @Override
    protected ProtocolVersion build(ProtocolVersion.ProtocolVersionBuilder builder) {
        return builder.build();
    }

    @Override
    protected EncodingType getEncodingType() {
        return ProtocolVersion.encodingType;
    }
}