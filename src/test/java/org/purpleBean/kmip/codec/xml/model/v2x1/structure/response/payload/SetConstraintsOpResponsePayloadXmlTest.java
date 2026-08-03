package org.purpleBean.kmip.codec.xml.model.v2x1.structure.response.payload;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.model.v2x1.structure.response.payload.SetConstraintsOpResponsePayload;
import org.purpleBean.kmip.test.suite.AbstractXmlSerializationTestSuite;

@DisplayName("SetConstraintsOpResponsePayload Xml Serialization Tests")
class SetConstraintsOpResponsePayloadXmlTest
    extends AbstractXmlSerializationTestSuite<SetConstraintsOpResponsePayload> {

  @Override
  public Class<SetConstraintsOpResponsePayload> type() {
    return SetConstraintsOpResponsePayload.class;
  }

  @Override
  public SetConstraintsOpResponsePayload createDefault() {
    return SetConstraintsOpResponsePayload
        .builder()
        .build();
  }

  @Override
  public SetConstraintsOpResponsePayload createVariant() {
    return SetConstraintsOpResponsePayload
        .builder()
        .build();
  }
}