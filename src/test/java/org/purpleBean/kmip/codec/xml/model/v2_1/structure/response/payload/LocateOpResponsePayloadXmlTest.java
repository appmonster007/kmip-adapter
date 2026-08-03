package org.purpleBean.kmip.codec.xml.model.v2_1.structure.response.payload;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.model.v2_1.structure.response.payload.LocateOpResponsePayload;
import org.purpleBean.kmip.test.suite.AbstractXmlSerializationTestSuite;

@DisplayName("LocateOpResponsePayload Xml Serialization Tests")
class LocateOpResponsePayloadXmlTest
    extends AbstractXmlSerializationTestSuite<LocateOpResponsePayload> {

  @Override
  public Class<LocateOpResponsePayload> type() {
    return LocateOpResponsePayload.class;
  }

  @Override
  public LocateOpResponsePayload createDefault() {
    return LocateOpResponsePayload
        .builder()
        .build();
  }

  @Override
  public LocateOpResponsePayload createVariant() {
    return LocateOpResponsePayload
        .builder()
        .build();
  }
}
