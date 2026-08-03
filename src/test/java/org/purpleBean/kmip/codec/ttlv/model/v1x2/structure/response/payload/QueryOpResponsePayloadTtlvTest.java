package org.purplebean.kmip.codec.ttlv.model.v1x2.structure.response.payload;

import org.junit.jupiter.api.DisplayName;
import org.purplebean.kmip.api.KmipSpec;
import org.purplebean.kmip.model.core.enumeration.Operation;
import org.purplebean.kmip.model.v1x2.structure.response.payload.QueryOpResponsePayload;
import org.purplebean.kmip.test.suite.AbstractTtlvSerializationTestSuite;

@DisplayName("QueryOpResponsePayload Ttlv Serialization Tests")
class QueryOpResponsePayloadTtlvTest
    extends AbstractTtlvSerializationTestSuite<QueryOpResponsePayload> {

  @Override
  protected void setupDefaultSpec() {
    defaultSpec = KmipSpec.V1_2;
  }

  @Override
  public Class<QueryOpResponsePayload> type() {
    return QueryOpResponsePayload.class;
  }

  @Override
  public QueryOpResponsePayload createDefault() {
    return QueryOpResponsePayload
        .builder()
        .operation(Operation.of(Operation.Standard.QUERY))
        .build();
  }

  @Override
  public QueryOpResponsePayload createVariant() {
    return QueryOpResponsePayload
        .builder()
        .operation(Operation.of(Operation.Standard.CREATE))
        .build();
  }
}
