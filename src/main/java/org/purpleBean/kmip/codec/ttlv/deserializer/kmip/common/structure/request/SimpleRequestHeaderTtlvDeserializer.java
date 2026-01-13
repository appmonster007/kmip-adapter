package org.purpleBean.kmip.codec.ttlv.deserializer.kmip.common.structure.request;

import org.purpleBean.kmip.EncodingType;
import org.purpleBean.kmip.KmipTag;
import org.purpleBean.kmip.ProtocolVersion;
import org.purpleBean.kmip.codec.ttlv.deserializer.kmip.AbstractKmipStructureTtlvDeserializer;
import org.purpleBean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purpleBean.kmip.common.structure.request.SimpleRequestHeader;

import java.io.IOException;
import java.nio.ByteBuffer;

public class SimpleRequestHeaderTtlvDeserializer extends AbstractKmipStructureTtlvDeserializer<SimpleRequestHeader, SimpleRequestHeader.SimpleRequestHeaderBuilder> {

    public SimpleRequestHeaderTtlvDeserializer() {
        super(SimpleRequestHeader.kmipTag);
    }

    @Override
    protected SimpleRequestHeader.SimpleRequestHeaderBuilder createBuilder() {
        return SimpleRequestHeader.builder();
    }

    @Override
    protected void setValue(SimpleRequestHeader.SimpleRequestHeaderBuilder builder, KmipTag.Value nodeTag, ByteBuffer p, TtlvMapper mapper) throws IOException {
        switch (nodeTag) {
            case KmipTag.Standard.PROTOCOL_VERSION ->
                    builder.protocolVersion(mapper.readValue(p, ProtocolVersion.class));
            default -> throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
        }
    }

    @Override
    protected SimpleRequestHeader build(SimpleRequestHeader.SimpleRequestHeaderBuilder builder) {
        return builder.build();
    }

    @Override
    protected EncodingType getEncodingType() {
        return SimpleRequestHeader.encodingType;
    }
}