package org.purpleBean.kmip.codec.ttlv.model.v2_1.structure.request.payload;

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
import org.purpleBean.kmip.model.v2_1.structure.request.payload.LogOpRequestPayload;
import org.purpleBean.kmip.test.suite.AbstractTtlvSerializationTestSuite;

@DisplayName("LogOpRequestPayload Ttlv Serialization Tests")
class LogOpRequestPayloadTtlvTest extends AbstractTtlvSerializationTestSuite<LogOpRequestPayload> {

    @Override
    public Class<LogOpRequestPayload> type() {
        return LogOpRequestPayload.class;
    }

    @Override
    public LogOpRequestPayload createDefault() {
        return LogOpRequestPayload.builder().build();
    }

    @Override
    public LogOpRequestPayload createVariant() {
        return LogOpRequestPayload.builder().build();
    }
}