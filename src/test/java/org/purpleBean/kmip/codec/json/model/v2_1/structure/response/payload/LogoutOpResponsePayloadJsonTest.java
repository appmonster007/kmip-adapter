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
import org.purpleBean.kmip.model.v2_1.structure.response.payload.LogoutOpResponsePayload;
import org.purpleBean.kmip.test.suite.AbstractJsonSerializationTestSuite;

@DisplayName("LogoutOpResponsePayload Json Serialization Tests")
class LogoutOpResponsePayloadJsonTest extends AbstractJsonSerializationTestSuite<LogoutOpResponsePayload> {

    @Override
    public Class<LogoutOpResponsePayload> type() {
        return LogoutOpResponsePayload.class;
    }

    @Override
    public LogoutOpResponsePayload createDefault() {
        return LogoutOpResponsePayload.builder().build();
    }

    @Override
    public LogoutOpResponsePayload createVariant() {
        return LogoutOpResponsePayload.builder().build();
    }
}