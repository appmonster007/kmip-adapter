package org.purpleBean.kmip.codec.ttlv.deserializer.model.core.enumeration;

import org.purpleBean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purpleBean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purpleBean.kmip.model.core.enumeration.ProtectionLevel;

import java.io.IOException;
import java.nio.ByteBuffer;

public class ProtectionLevelTtlvDeserializer extends AbstractKmipDataTypeTtlvDeserializer<ProtectionLevel, ProtectionLevel.ProtectionLevelBuilder> {

    public ProtectionLevelTtlvDeserializer() {
        super(ProtectionLevel.kmipTag, ProtectionLevel.encodingType);
    }

    @Override
    protected ProtectionLevel.ProtectionLevelBuilder createBuilder() {
        return ProtectionLevel.builder();
    }

    @Override
    protected void setValue(ProtectionLevel.ProtectionLevelBuilder builder, byte[] tag, byte type, ByteBuffer p, TtlvMapper mapper) throws IOException {
        Integer value = mapper.readValue(p, Integer.class);
        builder.value(ProtectionLevel.fromValue(value));
    }

    @Override
    protected ProtectionLevel build(ProtectionLevel.ProtectionLevelBuilder builder) {
        return builder.build();
    }
}
