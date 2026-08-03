package org.purplebean.kmip.codec.xml.model.v2x1.structure.response.payload;

import org.junit.jupiter.api.DisplayName;
import org.purplebean.kmip.model.v2x1.structure.response.payload.SetDefaultsOpResponsePayload;
import org.purplebean.kmip.test.suite.AbstractXmlSerializationTestSuite;

@DisplayName("SetDefaultsOpResponsePayload Xml Serialization Tests")
class SetDefaultsOpResponsePayloadXmlTest
    extends AbstractXmlSerializationTestSuite<SetDefaultsOpResponsePayload> {

  @Override
  public Class<SetDefaultsOpResponsePayload> type() {
    return SetDefaultsOpResponsePayload.class;
  }

  @Override
  public SetDefaultsOpResponsePayload createDefault() {
    return SetDefaultsOpResponsePayload
        .builder()
        .build();
  }

  @Override
  public SetDefaultsOpResponsePayload createVariant() {
    return SetDefaultsOpResponsePayload
        .builder()
        .build();
  }
}