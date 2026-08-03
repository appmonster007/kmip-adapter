package org.purpleBean.kmip.codec.xml.model.v2x1.structure.response.payload;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.model.core.enumeration.Operation;
import org.purpleBean.kmip.model.v2x1.structure.response.payload.QueryOpResponsePayload;
import org.purpleBean.kmip.test.suite.AbstractXmlSerializationTestSuite;

@DisplayName("QueryOpResponsePayload Xml Serialization Tests")
class QueryOpResponsePayloadXmlTest
    extends AbstractXmlSerializationTestSuite<QueryOpResponsePayload> {

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