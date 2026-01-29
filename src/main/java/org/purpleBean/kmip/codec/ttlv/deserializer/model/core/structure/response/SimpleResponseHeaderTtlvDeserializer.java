package org.purpleBean.kmip.codec.ttlv.deserializer.model.core.structure.response;

import org.purpleBean.kmip.api.KmipDataType;
import org.purpleBean.kmip.api.KmipTag;
import org.purpleBean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purpleBean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purpleBean.kmip.model.core.structure.ProtocolVersion;
import org.purpleBean.kmip.model.core.structure.response.SimpleResponseHeader;

import java.io.IOException;
import java.nio.ByteBuffer;

public class SimpleResponseHeaderTtlvDeserializer extends AbstractKmipDataTypeTtlvDeserializer<SimpleResponseHeader, SimpleResponseHeader.SimpleResponseHeaderBuilder> {

    public SimpleResponseHeaderTtlvDeserializer() {
        super(SimpleResponseHeader.kmipTag, SimpleResponseHeader.encodingType);
    }

    @Override
    protected SimpleResponseHeader.SimpleResponseHeaderBuilder createBuilder() {
        return SimpleResponseHeader.builder();
    }

    @Override
    protected void setValue(SimpleResponseHeader.SimpleResponseHeaderBuilder builder, byte[] tagBytes, byte type, ByteBuffer p, TtlvMapper mapper) throws IOException {
        KmipTag.Value nodeTag = KmipTag.fromBytes(tagBytes);
        switch (nodeTag) {
            case KmipTag.Standard.PROTOCOL_VERSION ->
                    builder.protocolVersion(mapper.readValue(p, ProtocolVersion.class));
            default -> {
                mapper.readValue(p, KmipDataType.class);
            }
        }
    }

    @Override
    protected SimpleResponseHeader build(SimpleResponseHeader.SimpleResponseHeaderBuilder builder) {
        return builder.build();
    }
}
