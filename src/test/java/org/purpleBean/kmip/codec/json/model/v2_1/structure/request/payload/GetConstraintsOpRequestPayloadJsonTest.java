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
import org.purpleBean.kmip.model.core.type.UniqueIdentifier;
import org.purpleBean.kmip.model.v2_1.structure.request.payload.GetConstraintsOpRequestPayload;
import org.purpleBean.kmip.test.suite.AbstractJsonSerializationTestSuite;

@DisplayName("GetConstraintsOpRequestPayload Json Serialization Tests")
class GetConstraintsOpRequestPayloadJsonTest extends AbstractJsonSerializationTestSuite<GetConstraintsOpRequestPayload> {

    @Override
    public Class<GetConstraintsOpRequestPayload> type() {
        return GetConstraintsOpRequestPayload.class;
    }

    @Override
    public GetConstraintsOpRequestPayload createDefault() {
        return GetConstraintsOpRequestPayload.builder().build();
    }

    @Override
    public GetConstraintsOpRequestPayload createVariant() {
        return GetConstraintsOpRequestPayload.builder().uniqueIdentifier(UniqueIdentifier.builder().value("test-uid-1").build()).build();
    }
}