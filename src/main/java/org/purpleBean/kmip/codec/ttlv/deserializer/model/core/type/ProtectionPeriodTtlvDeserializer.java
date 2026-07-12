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
import org.purpleBean.kmip.model.core.type.ProtectionPeriod;

import java.io.IOException;

public class ProtectionPeriodTtlvDeserializer extends AbstractKmipDataTypeTtlvDeserializer<ProtectionPeriod, ProtectionPeriod.ProtectionPeriodBuilder> {

    public ProtectionPeriodTtlvDeserializer() {
        super(ProtectionPeriod.kmipTag, ProtectionPeriod.encodingType);
    }

    @Override
    protected ProtectionPeriod.ProtectionPeriodBuilder createBuilder() {
        return ProtectionPeriod.builder();
    }

    @Override
    protected void setValue(ProtectionPeriod.ProtectionPeriodBuilder builder, byte[] tag, byte type, ByteBuffer p, TtlvMapper mapper) throws IOException {
        builder.value(mapper.readValue(p, Long.class));
    }

    @Override
    protected ProtectionPeriod build(ProtectionPeriod.ProtectionPeriodBuilder builder) {
        return builder.build();
    }
}