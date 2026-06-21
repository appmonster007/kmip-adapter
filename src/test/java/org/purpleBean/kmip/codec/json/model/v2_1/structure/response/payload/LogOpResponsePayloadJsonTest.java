package org.purpleBean.kmip.codec.json.model.v2_1.structure.response.payload;

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
import org.purpleBean.kmip.model.v2_1.structure.response.payload.LogOpResponsePayload;
import org.purpleBean.kmip.test.suite.AbstractJsonSerializationTestSuite;

@DisplayName("LogOpResponsePayload Json Serialization Tests")
class LogOpResponsePayloadJsonTest extends AbstractJsonSerializationTestSuite<LogOpResponsePayload> {

    @Override
    public Class<LogOpResponsePayload> type() {
        return LogOpResponsePayload.class;
    }

    @Override
    public LogOpResponsePayload createDefault() {
        return LogOpResponsePayload.builder().build();
    }

    @Override
    public LogOpResponsePayload createVariant() {
        return LogOpResponsePayload.builder().build();
    }
}