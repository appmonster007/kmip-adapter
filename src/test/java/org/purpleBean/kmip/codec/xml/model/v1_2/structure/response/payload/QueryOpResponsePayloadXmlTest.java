package org.purpleBean.kmip.codec.xml.model.v1_2.structure.response.payload;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.model.core.enumeration.Operation;
import org.purpleBean.kmip.model.v1_2.structure.response.payload.QueryOpResponsePayload;
import org.purpleBean.kmip.test.suite.AbstractXmlSerializationTestSuite;

@DisplayName("QueryOpResponsePayload Xml Serialization Tests")
class QueryOpResponsePayloadXmlTest
    extends AbstractXmlSerializationTestSuite<QueryOpResponsePayload> {

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
