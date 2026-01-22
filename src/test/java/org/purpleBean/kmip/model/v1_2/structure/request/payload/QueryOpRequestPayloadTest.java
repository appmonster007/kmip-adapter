package org.purpleBean.kmip.model.v1_2.structure.request.payload;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.api.EncodingType;
import org.purpleBean.kmip.api.KmipDataType;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.model.core.enumeration.QueryFunction;
import org.purpleBean.kmip.test.suite.AbstractKmipStructureTestSuite;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("QueryOpRequestPayload Domain Tests")
class QueryOpRequestPayloadTest extends AbstractKmipStructureTestSuite<QueryOpRequestPayload> {

    @Override
    protected void setupDefaultSpec() {
        defaultSpec = KmipSpec.V1_2;
    }

    @Override
    protected Class<QueryOpRequestPayload> type() {
        return QueryOpRequestPayload.class;
    }

    @Override
    protected QueryOpRequestPayload createDefault() {
        return QueryOpRequestPayload.builder()
                .queryFunction(QueryFunction.of(QueryFunction.Standard.QUERY_OPERATIONS))
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
        assertThat(values).hasSize(1);
        assertThat(values.get(0)).isInstanceOf(QueryFunction.class);
    }
}
