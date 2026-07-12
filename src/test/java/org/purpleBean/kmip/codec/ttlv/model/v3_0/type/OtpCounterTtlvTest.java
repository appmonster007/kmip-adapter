package org.purpleBean.kmip.codec.ttlv.model.v3_0.type;

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
import org.purpleBean.kmip.model.v3_0.type.OtpCounter;
import org.purpleBean.kmip.test.suite.AbstractTtlvSerializationTestSuite;

@DisplayName("OtpCounter Ttlv Serialization Tests")
class OtpCounterTtlvTest extends AbstractTtlvSerializationTestSuite<OtpCounter> {

    @Override
    public Class<OtpCounter> type() {
        return OtpCounter.class;
    }

    @Override
    public OtpCounter createDefault() {
        return OtpCounter.of(123);
    }

    @Override
    public OtpCounter createVariant() {
        return OtpCounter.of(456);
    }
}