package org.purpleBean.kmip.codec.xml.model.v2_1.structure.request.payload;

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
import org.purpleBean.kmip.model.v2_1.structure.request.payload.QueryAsynchronousRequestsOpRequestPayload;
import org.purpleBean.kmip.test.suite.AbstractXmlSerializationTestSuite;

@DisplayName("QueryAsynchronousRequestsOpRequestPayload Xml Serialization Tests")
class QueryAsynchronousRequestsOpRequestPayloadXmlTest extends AbstractXmlSerializationTestSuite<QueryAsynchronousRequestsOpRequestPayload> {

    @Override
    public Class<QueryAsynchronousRequestsOpRequestPayload> type() {
        return QueryAsynchronousRequestsOpRequestPayload.class;
    }

    @Override
    public QueryAsynchronousRequestsOpRequestPayload createDefault() {
        return QueryAsynchronousRequestsOpRequestPayload.builder().build();
    }

    @Override
    public QueryAsynchronousRequestsOpRequestPayload createVariant() {
        return QueryAsynchronousRequestsOpRequestPayload.builder().build();
    }
}