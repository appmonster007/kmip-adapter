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
import org.purpleBean.kmip.model.v2_1.structure.response.payload.QueryAsynchronousRequestsOpResponsePayload;
import org.purpleBean.kmip.test.suite.AbstractJsonSerializationTestSuite;

@DisplayName("QueryAsynchronousRequestsOpResponsePayload Json Serialization Tests")
class QueryAsynchronousRequestsOpResponsePayloadJsonTest extends AbstractJsonSerializationTestSuite<QueryAsynchronousRequestsOpResponsePayload> {

    @Override
    public Class<QueryAsynchronousRequestsOpResponsePayload> type() {
        return QueryAsynchronousRequestsOpResponsePayload.class;
    }

    @Override
    public QueryAsynchronousRequestsOpResponsePayload createDefault() {
        return QueryAsynchronousRequestsOpResponsePayload.builder().build();
    }

    @Override
    public QueryAsynchronousRequestsOpResponsePayload createVariant() {
        return QueryAsynchronousRequestsOpResponsePayload.builder().build();
    }
}