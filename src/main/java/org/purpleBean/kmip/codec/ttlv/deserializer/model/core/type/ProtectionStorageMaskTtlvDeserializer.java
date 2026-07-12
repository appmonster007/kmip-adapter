package org.purpleBean.kmip.codec.ttlv.deserializer.model.core.type;

import org.purpleBean.kmip.*;
import org.purpleBean.kmip.api.*;
import org.purpleBean.kmip.model.core.enumeration.*;
import org.purpleBean.kmip.model.core.structure.*;
import org.purpleBean.kmip.model.core.type.*;
import java.math.BigInteger;
import java.nio.ByteBuffer;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import org.purpleBean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purpleBean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purpleBean.kmip.model.core.type.ProtectionStorageMask;

import java.io.IOException;

public class ProtectionStorageMaskTtlvDeserializer extends AbstractKmipDataTypeTtlvDeserializer<ProtectionStorageMask, ProtectionStorageMask.ProtectionStorageMaskBuilder> {

    public ProtectionStorageMaskTtlvDeserializer() {
        super(ProtectionStorageMask.kmipTag, ProtectionStorageMask.encodingType);
    }

    @Override
    protected ProtectionStorageMask.ProtectionStorageMaskBuilder createBuilder() {
        return ProtectionStorageMask.builder();
    }

    @Override
    protected void setValue(ProtectionStorageMask.ProtectionStorageMaskBuilder builder, byte[] tag, byte type, ByteBuffer p, TtlvMapper mapper) throws IOException {
        builder.value(mapper.readValue(p, Integer.class));
    }

    @Override
    protected ProtectionStorageMask build(ProtectionStorageMask.ProtectionStorageMaskBuilder builder) {
        return builder.build();
    }
}