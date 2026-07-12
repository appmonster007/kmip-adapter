package org.purpleBean.kmip.codec.ttlv.deserializer.model.v2_1.enumeration;

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
import org.purpleBean.kmip.model.v2_1.enumeration.AsynchronousIndicator;

import java.io.IOException;

public class AsynchronousIndicatorTtlvDeserializer extends AbstractKmipDataTypeTtlvDeserializer<AsynchronousIndicator, AsynchronousIndicator.AsynchronousIndicatorBuilder> {

    public AsynchronousIndicatorTtlvDeserializer() {
        super(AsynchronousIndicator.kmipTag, AsynchronousIndicator.encodingType);
    }

    @Override
    protected AsynchronousIndicator.AsynchronousIndicatorBuilder createBuilder() {
        return AsynchronousIndicator.builder();
    }

    @Override
    protected void setValue(AsynchronousIndicator.AsynchronousIndicatorBuilder builder, byte[] tag, byte type, ByteBuffer p, TtlvMapper mapper) throws IOException {
        builder.value(AsynchronousIndicator.fromValue(mapper.readValue(p, Integer.class)));
    }

    @Override
    protected AsynchronousIndicator build(AsynchronousIndicator.AsynchronousIndicatorBuilder builder) {
        return builder.build();
    }
}