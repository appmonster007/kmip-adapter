package org.purpleBean.kmip.codec.xml.model.v2_1.structure.request.payload;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.model.v2_1.structure.request.payload.MacOpRequestPayload;
import org.purpleBean.kmip.test.suite.AbstractXmlSerializationTestSuite;

@DisplayName("MacOpRequestPayload Xml Serialization Tests")
class MacOpRequestPayloadXmlTest extends AbstractXmlSerializationTestSuite<MacOpRequestPayload> {

  @Override
  public Class<MacOpRequestPayload> type() {
    return MacOpRequestPayload.class;
  }

  @Override
  public MacOpRequestPayload createDefault() {
    return MacOpRequestPayload
        .builder()
        .build();
  }

  @Override
  public MacOpRequestPayload createVariant() {
    return MacOpRequestPayload
        .builder()
        .build();
  }
}