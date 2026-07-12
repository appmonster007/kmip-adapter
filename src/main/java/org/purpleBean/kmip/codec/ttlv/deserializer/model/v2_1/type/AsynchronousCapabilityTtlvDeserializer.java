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
import org.purpleBean.kmip.model.v2_1.type.AsynchronousCapability;

import java.io.IOException;

public class AsynchronousCapabilityTtlvDeserializer extends AbstractKmipDataTypeTtlvDeserializer<AsynchronousCapability, AsynchronousCapability.AsynchronousCapabilityBuilder> {

    public AsynchronousCapabilityTtlvDeserializer() {
        super(AsynchronousCapability.kmipTag, AsynchronousCapability.encodingType);
    }

    @Override
    protected AsynchronousCapability.AsynchronousCapabilityBuilder createBuilder() {
        return AsynchronousCapability.builder();
    }

    @Override
    protected void setValue(AsynchronousCapability.AsynchronousCapabilityBuilder builder, byte[] tag, byte type, ByteBuffer p, TtlvMapper mapper) throws IOException {
        builder.value(mapper.readValue(p, Boolean.class));
    }

    @Override
    protected AsynchronousCapability build(AsynchronousCapability.AsynchronousCapabilityBuilder builder) {
        return builder.build();
    }
}