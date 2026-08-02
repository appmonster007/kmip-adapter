package org.purpleBean.kmip.model.v2_1.structure.request.payload;

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
import org.purpleBean.kmip.test.suite.AbstractKmipStructureTestSuite;


import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("QueryOpRequestPayload Domain Tests")
class QueryOpRequestPayloadTest extends AbstractKmipStructureTestSuite<QueryOpRequestPayload> {

    @Override
    protected void setupDefaultSpec() {
        defaultSpec = KmipSpec.V2_1;
    }

    @Override
    protected Class<QueryOpRequestPayload> type() {
        return QueryOpRequestPayload.class;
    }

    @Override
    protected QueryOpRequestPayload createDefault() {
        return QueryOpRequestPayload.builder()
                .queryFunction(QueryFunction.Standard.QUERY_OPERATIONS.inst())
                .build();
    }

    @Override
    protected EncodingType expectedEncodingType() {
        return EncodingType.STRUCTURE;
    }

    @Override
    protected int expectedMinComponentCount() {
        return 1;
    }

    @Override
    protected void validateComponents(List<KmipDataType> values) {
        assertThat(values).hasSizeGreaterThanOrEqualTo(1);
    }
}