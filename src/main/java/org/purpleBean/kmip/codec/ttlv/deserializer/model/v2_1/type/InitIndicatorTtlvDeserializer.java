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
import org.purpleBean.kmip.model.v2_1.type.InitIndicator;

import java.io.IOException;

public class InitIndicatorTtlvDeserializer extends AbstractKmipDataTypeTtlvDeserializer<InitIndicator, InitIndicator.InitIndicatorBuilder> {

    public InitIndicatorTtlvDeserializer() {
        super(InitIndicator.kmipTag, InitIndicator.encodingType);
    }

    @Override
    protected InitIndicator.InitIndicatorBuilder createBuilder() {
        return InitIndicator.builder();
    }

    @Override
    protected void setValue(InitIndicator.InitIndicatorBuilder builder, byte[] tag, byte type, ByteBuffer p, TtlvMapper mapper) throws IOException {
        builder.value(mapper.readValue(p, Boolean.class));
    }

    @Override
    protected InitIndicator build(InitIndicator.InitIndicatorBuilder builder) {
        return builder.build();
    }
}