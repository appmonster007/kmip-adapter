package org.purpleBean.kmip.codec.xml.model.v2x1.structure.request.payload;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.model.v2x1.structure.request.payload.QueryAsynchronousRequestsOpRequestPayload;
import org.purpleBean.kmip.test.suite.AbstractXmlSerializationTestSuite;

@DisplayName("QueryAsynchronousRequestsOpRequestPayload Xml Serialization Tests")
class QueryAsynchronousRequestsOpRequestPayloadXmlTest
    extends AbstractXmlSerializationTestSuite<QueryAsynchronousRequestsOpRequestPayload> {

  @Override
  public Class<QueryAsynchronousRequestsOpRequestPayload> type() {
    return QueryAsynchronousRequestsOpRequestPayload.class;
  }

  @Override
  public QueryAsynchronousRequestsOpRequestPayload createDefault() {
    return QueryAsynchronousRequestsOpRequestPayload
        .builder()
        .build();
  }

  @Override
  public QueryAsynchronousRequestsOpRequestPayload createVariant() {
    return QueryAsynchronousRequestsOpRequestPayload
        .builder()
        .build();
  }
}