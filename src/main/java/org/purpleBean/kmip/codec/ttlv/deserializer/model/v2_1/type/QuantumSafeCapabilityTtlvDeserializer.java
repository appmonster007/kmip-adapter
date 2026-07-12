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
import org.purpleBean.kmip.model.v2_1.type.QuantumSafeCapability;

import java.io.IOException;

public class QuantumSafeCapabilityTtlvDeserializer extends AbstractKmipDataTypeTtlvDeserializer<QuantumSafeCapability, QuantumSafeCapability.QuantumSafeCapabilityBuilder> {

    public QuantumSafeCapabilityTtlvDeserializer() {
        super(QuantumSafeCapability.kmipTag, QuantumSafeCapability.encodingType);
    }

    @Override
    protected QuantumSafeCapability.QuantumSafeCapabilityBuilder createBuilder() {
        return QuantumSafeCapability.builder();
    }

    @Override
    protected void setValue(QuantumSafeCapability.QuantumSafeCapabilityBuilder builder, byte[] tag, byte type, ByteBuffer p, TtlvMapper mapper) throws IOException {
        builder.value(mapper.readValue(p, Boolean.class));
    }

    @Override
    protected QuantumSafeCapability build(QuantumSafeCapability.QuantumSafeCapabilityBuilder builder) {
        return builder.build();
    }
}