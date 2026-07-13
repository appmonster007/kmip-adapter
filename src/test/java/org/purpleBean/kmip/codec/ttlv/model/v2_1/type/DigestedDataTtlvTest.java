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
import org.purpleBean.kmip.model.v2_1.type.DigestedData;
import org.purpleBean.kmip.test.suite.AbstractTtlvSerializationTestSuite;

@DisplayName("DigestedData Ttlv Serialization Tests")
class DigestedDataTtlvTest extends AbstractTtlvSerializationTestSuite<DigestedData> {

    @Override
    public Class<DigestedData> type() {
        return DigestedData.class;
    }

    @Override
    public DigestedData createDefault() {
        return DigestedData.of(ByteBuffer.wrap(new byte[]{0x01, 0x02, 0x03}));
    }

    @Override
    public DigestedData createVariant() {
        return DigestedData.of(ByteBuffer.wrap(new byte[]{0x04, 0x05, 0x06}));
    }
}