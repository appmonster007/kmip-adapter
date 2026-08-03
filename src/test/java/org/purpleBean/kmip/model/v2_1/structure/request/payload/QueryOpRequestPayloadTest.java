package org.purpleBean.kmip.model.v2_1.structure.request.payload;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.api.EncodingType;
import org.purpleBean.kmip.api.KmipDataType;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.model.core.enumeration.QueryFunction;
import org.purpleBean.kmip.test.suite.AbstractKmipStructureTestSuite;

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
    return QueryOpRequestPayload
        .builder()
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