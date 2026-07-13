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
import org.purpleBean.kmip.model.v2_1.type.CorrelationValue;
import org.purpleBean.kmip.test.suite.AbstractTtlvSerializationTestSuite;

@DisplayName("CorrelationValue Ttlv Serialization Tests")
class CorrelationValueTtlvTest extends AbstractTtlvSerializationTestSuite<CorrelationValue> {

    @Override
    public Class<CorrelationValue> type() {
        return CorrelationValue.class;
    }

    @Override
    public CorrelationValue createDefault() {
        return CorrelationValue.of(ByteBuffer.wrap(new byte[]{0x01, 0x02, 0x03}));
    }

    @Override
    public CorrelationValue createVariant() {
        return CorrelationValue.of(ByteBuffer.wrap(new byte[]{0x04, 0x05, 0x06}));
    }
}