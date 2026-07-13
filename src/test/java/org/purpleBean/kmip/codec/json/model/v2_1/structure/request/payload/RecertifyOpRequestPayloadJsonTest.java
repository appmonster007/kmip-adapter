package org.purpleBean.kmip.codec.json.model.v2_1.structure.request.payload;

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
import org.purpleBean.kmip.model.v2_1.structure.request.payload.RecertifyOpRequestPayload;
import org.purpleBean.kmip.test.suite.AbstractJsonSerializationTestSuite;

@DisplayName("RecertifyOpRequestPayload Json Serialization Tests")
class RecertifyOpRequestPayloadJsonTest extends AbstractJsonSerializationTestSuite<RecertifyOpRequestPayload> {

    @Override
    public Class<RecertifyOpRequestPayload> type() {
        return RecertifyOpRequestPayload.class;
    }

    @Override
    public RecertifyOpRequestPayload createDefault() {
        return RecertifyOpRequestPayload.builder().build();
    }

    @Override
    public RecertifyOpRequestPayload createVariant() {
        return RecertifyOpRequestPayload.builder().build();
    }
}