package org.purpleBean.kmip.codec.xml.model.v2x1.structure.request.payload;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.model.v2x1.structure.request.payload.RecertifyOpRequestPayload;
import org.purpleBean.kmip.test.suite.AbstractXmlSerializationTestSuite;

@DisplayName("RecertifyOpRequestPayload Xml Serialization Tests")
class RecertifyOpRequestPayloadXmlTest
    extends AbstractXmlSerializationTestSuite<RecertifyOpRequestPayload> {

  @Override
  public Class<RecertifyOpRequestPayload> type() {
    return RecertifyOpRequestPayload.class;
  }

  @Override
  public RecertifyOpRequestPayload createDefault() {
    return RecertifyOpRequestPayload
        .builder()
        .build();
  }

  @Override
  public RecertifyOpRequestPayload createVariant() {
    return RecertifyOpRequestPayload
        .builder()
        .build();
  }
}