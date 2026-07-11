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
import org.purpleBean.kmip.model.v2_1.type.StreamingCapability;
import org.purpleBean.kmip.test.suite.AbstractTtlvSerializationTestSuite;

@DisplayName("StreamingCapability Ttlv Serialization Tests")
class StreamingCapabilityTtlvTest extends AbstractTtlvSerializationTestSuite<StreamingCapability> {

    @Override
    public Class<StreamingCapability> type() {
        return StreamingCapability.class;
    }

    @Override
    public StreamingCapability createDefault() {
        return StreamingCapability.of(true);
    }

    @Override
    public StreamingCapability createVariant() {
        return StreamingCapability.of(false);
    }
}