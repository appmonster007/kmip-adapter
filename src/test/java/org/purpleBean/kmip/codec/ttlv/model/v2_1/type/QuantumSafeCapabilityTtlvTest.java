package org.purpleBean.kmip.codec.ttlv.model.v2_1.type;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.*;
import org.purpleBean.kmip.api.*;
import org.purpleBean.kmip.model.core.enumeration.*;
import org.purpleBean.kmip.model.core.structure.*;
import org.purpleBean.kmip.model.core.type.*;
import java.math.BigInteger;
import java.nio.ByteBuffer;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import org.purpleBean.kmip.model.v2_1.type.QuantumSafeCapability;
import org.purpleBean.kmip.test.suite.AbstractTtlvSerializationTestSuite;

@DisplayName("QuantumSafeCapability Ttlv Serialization Tests")
class QuantumSafeCapabilityTtlvTest extends AbstractTtlvSerializationTestSuite<QuantumSafeCapability> {

    @Override
    public Class<QuantumSafeCapability> type() {
        return QuantumSafeCapability.class;
    }

    @Override
    public QuantumSafeCapability createDefault() {
        return QuantumSafeCapability.of(true);
    }

    @Override
    public QuantumSafeCapability createVariant() {
        return QuantumSafeCapability.of(false);
    }
}