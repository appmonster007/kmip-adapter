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
import org.purpleBean.kmip.model.core.type.RotateLatest;

import java.io.IOException;

public class RotateLatestTtlvDeserializer extends AbstractKmipDataTypeTtlvDeserializer<RotateLatest, RotateLatest.RotateLatestBuilder> {

    public RotateLatestTtlvDeserializer() {
        super(RotateLatest.kmipTag, RotateLatest.encodingType);
    }

    @Override
    protected RotateLatest.RotateLatestBuilder createBuilder() {
        return RotateLatest.builder();
    }

    @Override
    protected void setValue(RotateLatest.RotateLatestBuilder builder, byte[] tag, byte type, ByteBuffer p, TtlvMapper mapper) throws IOException {
        builder.value(mapper.readValue(p, Boolean.class));
    }

    @Override
    protected RotateLatest build(RotateLatest.RotateLatestBuilder builder) {
        return builder.build();
    }
}