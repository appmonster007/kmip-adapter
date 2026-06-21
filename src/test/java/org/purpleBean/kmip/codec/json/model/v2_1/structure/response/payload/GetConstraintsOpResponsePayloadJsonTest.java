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
import org.purpleBean.kmip.model.v2_1.structure.response.payload.GetConstraintsOpResponsePayload;
import org.purpleBean.kmip.test.suite.AbstractJsonSerializationTestSuite;

@DisplayName("GetConstraintsOpResponsePayload Json Serialization Tests")
class GetConstraintsOpResponsePayloadJsonTest extends AbstractJsonSerializationTestSuite<GetConstraintsOpResponsePayload> {

    @Override
    public Class<GetConstraintsOpResponsePayload> type() {
        return GetConstraintsOpResponsePayload.class;
    }

    @Override
    public GetConstraintsOpResponsePayload createDefault() {
        return GetConstraintsOpResponsePayload.builder().build();
    }

    @Override
    public GetConstraintsOpResponsePayload createVariant() {
        return GetConstraintsOpResponsePayload.builder().build();
    }
}