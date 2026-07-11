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
import org.purpleBean.kmip.model.core.type.OtpSeed;

import java.io.IOException;

public class OtpSeedTtlvDeserializer extends AbstractKmipDataTypeTtlvDeserializer<OtpSeed, OtpSeed.OtpSeedBuilder> {

    public OtpSeedTtlvDeserializer() {
        super(OtpSeed.kmipTag, OtpSeed.encodingType);
    }

    @Override
    protected OtpSeed.OtpSeedBuilder createBuilder() {
        return OtpSeed.builder();
    }

    @Override
    protected void setValue(OtpSeed.OtpSeedBuilder builder, byte[] tag, byte type, ByteBuffer p, TtlvMapper mapper) throws IOException {
        builder.value(mapper.readValue(p, ByteBuffer.class));
    }

    @Override
    protected OtpSeed build(OtpSeed.OtpSeedBuilder builder) {
        return builder.build();
    }
}