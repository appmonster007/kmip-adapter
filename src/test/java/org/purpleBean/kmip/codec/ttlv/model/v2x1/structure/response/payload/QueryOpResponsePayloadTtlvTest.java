package org.purpleBean.kmip.codec.ttlv.model.v2x1.structure.response.payload;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.model.core.enumeration.Operation;
import org.purpleBean.kmip.model.v2x1.structure.response.payload.QueryOpResponsePayload;
import org.purpleBean.kmip.test.suite.AbstractTtlvSerializationTestSuite;

@DisplayName("QueryOpResponsePayload Ttlv Serialization Tests")
class QueryOpResponsePayloadTtlvTest
    extends AbstractTtlvSerializationTestSuite<QueryOpResponsePayload> {

  @Override
  public Class<QueryOpResponsePayload> type() {
    return QueryOpResponsePayload.class;
  }

  @Override
  public QueryOpResponsePayload createDefault() {
    return QueryOpResponsePayload
        .builder()
        .operation(Operation.Standard.QUERY.inst())
        .build();
  }

  @Override
  public QueryOpResponsePayload createVariant() {
    return QueryOpResponsePayload
        .builder()
        .operation(Operation.Standard.QUERY.inst())
        .build();
  }
}