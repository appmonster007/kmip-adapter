package org.purpleBean.kmip.codec.xml.model.v2x1.structure.request.payload;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.model.v2x1.structure.request.payload.PingOpRequestPayload;
import org.purpleBean.kmip.test.suite.AbstractXmlSerializationTestSuite;

@DisplayName("PingOpRequestPayload Xml Serialization Tests")
class PingOpRequestPayloadXmlTest extends AbstractXmlSerializationTestSuite<PingOpRequestPayload> {

  @Override
  public Class<PingOpRequestPayload> type() {
    return PingOpRequestPayload.class;
  }

  @Override
  public PingOpRequestPayload createDefault() {
    return PingOpRequestPayload
        .builder()
        .build();
  }

  @Override
  public PingOpRequestPayload createVariant() {
    return PingOpRequestPayload
        .builder()
        .build();
  }
}