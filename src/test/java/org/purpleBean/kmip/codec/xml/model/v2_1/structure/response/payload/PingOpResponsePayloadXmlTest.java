package org.purpleBean.kmip.codec.xml.model.v2_1.structure.response.payload;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.model.v2_1.structure.response.payload.PingOpResponsePayload;
import org.purpleBean.kmip.test.suite.AbstractXmlSerializationTestSuite;

@DisplayName("PingOpResponsePayload Xml Serialization Tests")
class PingOpResponsePayloadXmlTest
    extends AbstractXmlSerializationTestSuite<PingOpResponsePayload> {

  @Override
  public Class<PingOpResponsePayload> type() {
    return PingOpResponsePayload.class;
  }

  @Override
  public PingOpResponsePayload createDefault() {
    return PingOpResponsePayload
        .builder()
        .build();
  }

  @Override
  public PingOpResponsePayload createVariant() {
    return PingOpResponsePayload
        .builder()
        .build();
  }
}