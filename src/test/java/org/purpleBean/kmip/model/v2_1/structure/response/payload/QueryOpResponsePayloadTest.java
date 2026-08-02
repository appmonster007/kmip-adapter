package org.purpleBean.kmip.model.v2_1.structure.response.payload;

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

@DisplayName("QueryOpResponsePayload Domain Tests")
class QueryOpResponsePayloadTest extends AbstractKmipStructureTestSuite<QueryOpResponsePayload> {

    @Override
    protected void setupDefaultSpec() {
        defaultSpec = KmipSpec.V2_1;
    }

    @Override
    protected Class<QueryOpResponsePayload> type() {
        return QueryOpResponsePayload.class;
    }

    @Override
    protected QueryOpResponsePayload createDefault() {
        return QueryOpResponsePayload.builder()
                .operation(Operation.Standard.QUERY.inst())
                .build();
    }

    @Override
    protected EncodingType expectedEncodingType() {
        return EncodingType.STRUCTURE;
    }

    @Override
    protected int expectedMinComponentCount() {
        return 0;
    }

    @Override
    protected void validateComponents(List<KmipDataType> values) {
        assertThat(values).isNotNull();
    }
}