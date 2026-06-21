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
import org.purpleBean.kmip.model.v2_1.structure.response.payload.PingOpResponsePayload;
import org.purpleBean.kmip.test.suite.AbstractJsonSerializationTestSuite;

@DisplayName("PingOpResponsePayload Json Serialization Tests")
class PingOpResponsePayloadJsonTest extends AbstractJsonSerializationTestSuite<PingOpResponsePayload> {

    @Override
    public Class<PingOpResponsePayload> type() {
        return PingOpResponsePayload.class;
    }

    @Override
    public PingOpResponsePayload createDefault() {
        return PingOpResponsePayload.builder().build();
    }

    @Override
    public PingOpResponsePayload createVariant() {
        return PingOpResponsePayload.builder().build();
    }
}