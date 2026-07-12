package org.purpleBean.kmip.codec.ttlv.deserializer.model.v2_1.type;

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
import org.purpleBean.kmip.model.v2_1.type.RotateOffset;

import java.io.IOException;

public class RotateOffsetTtlvDeserializer extends AbstractKmipDataTypeTtlvDeserializer<RotateOffset, RotateOffset.RotateOffsetBuilder> {

    public RotateOffsetTtlvDeserializer() {
        super(RotateOffset.kmipTag, RotateOffset.encodingType);
    }

    @Override
    protected RotateOffset.RotateOffsetBuilder createBuilder() {
        return RotateOffset.builder();
    }

    @Override
    protected void setValue(RotateOffset.RotateOffsetBuilder builder, byte[] tag, byte type, ByteBuffer p, TtlvMapper mapper) throws IOException {
        builder.value(mapper.readValue(p, Long.class));
    }

    @Override
    protected RotateOffset build(RotateOffset.RotateOffsetBuilder builder) {
        return builder.build();
    }
}