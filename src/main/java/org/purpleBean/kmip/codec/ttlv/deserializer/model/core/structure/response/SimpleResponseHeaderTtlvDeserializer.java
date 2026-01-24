package org.purpleBean.kmip.codec.ttlv.deserializer.model.core.structure.response;

import org.purpleBean.kmip.api.EncodingType;
import org.purpleBean.kmip.api.KmipTag;
import org.purpleBean.kmip.codec.ttlv.deserializer.api.AbstractKmipStructureTtlvDeserializer;
import org.purpleBean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purpleBean.kmip.model.core.structure.ProtocolVersion;
import org.purpleBean.kmip.model.core.structure.response.SimpleResponseHeader;

import java.io.IOException;
import java.nio.ByteBuffer;

public class SimpleResponseHeaderTtlvDeserializer extends AbstractKmipStructureTtlvDeserializer<SimpleResponseHeader, SimpleResponseHeader.SimpleResponseHeaderBuilder> {

    public SimpleResponseHeaderTtlvDeserializer() {
        super(SimpleResponseHeader.kmipTag);
    }

    @Override
    protected SimpleResponseHeader.SimpleResponseHeaderBuilder createBuilder() {
        return SimpleResponseHeader.builder();
    }

    @Override
    protected void setValue(SimpleResponseHeader.SimpleResponseHeaderBuilder builder, KmipTag.Value nodeTag, ByteBuffer p, TtlvMapper mapper) throws IOException {
        switch (nodeTag) {
            case KmipTag.Standard.PROTOCOL_VERSION ->
                    builder.protocolVersion(mapper.readValue(p, ProtocolVersion.class));
            default -> {
            }
        }
    }

    @Override
    protected SimpleResponseHeader build(SimpleResponseHeader.SimpleResponseHeaderBuilder builder) {
        return builder.build();
    }

    @Override
    protected EncodingType getEncodingType() {
        return SimpleResponseHeader.encodingType;
    }
}
