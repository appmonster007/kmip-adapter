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
import org.purpleBean.kmip.model.v2_1.type.AlwaysSensitive;

import java.io.IOException;

public class AlwaysSensitiveTtlvDeserializer extends AbstractKmipDataTypeTtlvDeserializer<AlwaysSensitive, AlwaysSensitive.AlwaysSensitiveBuilder> {

    public AlwaysSensitiveTtlvDeserializer() {
        super(AlwaysSensitive.kmipTag, AlwaysSensitive.encodingType);
    }

    @Override
    protected AlwaysSensitive.AlwaysSensitiveBuilder createBuilder() {
        return AlwaysSensitive.builder();
    }

    @Override
    protected void setValue(AlwaysSensitive.AlwaysSensitiveBuilder builder, byte[] tag, byte type, ByteBuffer p, TtlvMapper mapper) throws IOException {
        builder.value(mapper.readValue(p, Boolean.class));
    }

    @Override
    protected AlwaysSensitive build(AlwaysSensitive.AlwaysSensitiveBuilder builder) {
        return builder.build();
    }
}