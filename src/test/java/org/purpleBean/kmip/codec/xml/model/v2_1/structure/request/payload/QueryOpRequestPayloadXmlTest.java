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
import org.purpleBean.kmip.model.v2_1.structure.request.payload.QueryOpRequestPayload;
import org.purpleBean.kmip.test.suite.AbstractXmlSerializationTestSuite;

@DisplayName("QueryOpRequestPayload Xml Serialization Tests")
class QueryOpRequestPayloadXmlTest extends AbstractXmlSerializationTestSuite<QueryOpRequestPayload> {

    @Override
    public Class<QueryOpRequestPayload> type() {
        return QueryOpRequestPayload.class;
    }

    @Override
    public QueryOpRequestPayload createDefault() {
        return QueryOpRequestPayload.builder().queryFunction(QueryFunction.Standard.QUERY_OPERATIONS.inst()).build();
    }

    @Override
    public QueryOpRequestPayload createVariant() {
        return QueryOpRequestPayload.builder().queryFunction(QueryFunction.Standard.QUERY_OPERATIONS.inst()).build();
    }
}