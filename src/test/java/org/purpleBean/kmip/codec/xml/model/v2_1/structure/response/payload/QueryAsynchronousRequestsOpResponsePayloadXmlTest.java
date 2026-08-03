package org.purpleBean.kmip.codec.xml.model.v2_1.structure.response.payload;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.model.v2_1.structure.response.payload.QueryAsynchronousRequestsOpResponsePayload;
import org.purpleBean.kmip.test.suite.AbstractXmlSerializationTestSuite;

@DisplayName("QueryAsynchronousRequestsOpResponsePayload Xml Serialization Tests")
class QueryAsynchronousRequestsOpResponsePayloadXmlTest
    extends AbstractXmlSerializationTestSuite<QueryAsynchronousRequestsOpResponsePayload> {

  @Override
  public Class<QueryAsynchronousRequestsOpResponsePayload> type() {
    return QueryAsynchronousRequestsOpResponsePayload.class;
  }

  @Override
  public QueryAsynchronousRequestsOpResponsePayload createDefault() {
    return QueryAsynchronousRequestsOpResponsePayload
        .builder()
        .build();
  }

  @Override
  public QueryAsynchronousRequestsOpResponsePayload createVariant() {
    return QueryAsynchronousRequestsOpResponsePayload
        .builder()
        .build();
  }
}