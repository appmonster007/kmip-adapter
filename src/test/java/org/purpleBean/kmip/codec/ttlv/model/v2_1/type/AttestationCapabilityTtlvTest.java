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
import org.purpleBean.kmip.model.v2_1.type.AttestationCapability;
import org.purpleBean.kmip.test.suite.AbstractTtlvSerializationTestSuite;

@DisplayName("AttestationCapability Ttlv Serialization Tests")
class AttestationCapabilityTtlvTest extends AbstractTtlvSerializationTestSuite<AttestationCapability> {

    @Override
    public Class<AttestationCapability> type() {
        return AttestationCapability.class;
    }

    @Override
    public AttestationCapability createDefault() {
        return AttestationCapability.of(true);
    }

    @Override
    public AttestationCapability createVariant() {
        return AttestationCapability.of(false);
    }
}