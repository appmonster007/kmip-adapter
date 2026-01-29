package org.purpleBean.kmip.codec.ttlv.deserializer.model.core.structure;

import org.purpleBean.kmip.api.KmipTag;
import org.purpleBean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purpleBean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purpleBean.kmip.model.core.structure.ProtocolVersion;
import org.purpleBean.kmip.model.core.type.ProtocolVersionMajor;
import org.purpleBean.kmip.model.core.type.ProtocolVersionMinor;

import java.io.IOException;
import java.nio.ByteBuffer;

public class ProtocolVersionTtlvDeserializer extends AbstractKmipDataTypeTtlvDeserializer<ProtocolVersion, ProtocolVersion.ProtocolVersionBuilder> {

    public ProtocolVersionTtlvDeserializer() {
        super(ProtocolVersion.kmipTag, ProtocolVersion.encodingType);
    }

    @Override
    protected ProtocolVersion.ProtocolVersionBuilder createBuilder() {
        return ProtocolVersion.builder();
    }

    @Override
    protected void setValue(ProtocolVersion.ProtocolVersionBuilder builder, byte[] tagBytes, ByteBuffer p, TtlvMapper mapper) throws IOException {
        KmipTag.Value nodeTag = KmipTag.fromBytes(tagBytes);
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
}