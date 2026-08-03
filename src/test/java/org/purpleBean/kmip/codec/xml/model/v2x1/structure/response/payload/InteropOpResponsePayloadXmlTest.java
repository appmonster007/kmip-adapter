package org.purplebean.kmip.codec.xml.model.v2x1.structure.response.payload;

import org.junit.jupiter.api.DisplayName;
import org.purplebean.kmip.model.v2x1.structure.response.payload.InteropOpResponsePayload;
import org.purplebean.kmip.test.suite.AbstractXmlSerializationTestSuite;

@DisplayName("InteropOpResponsePayload Xml Serialization Tests")
class InteropOpResponsePayloadXmlTest
    extends AbstractXmlSerializationTestSuite<InteropOpResponsePayload> {

  @Override
  public Class<InteropOpResponsePayload> type() {
    return InteropOpResponsePayload.class;
  }

  @Override
  public InteropOpResponsePayload createDefault() {
    return InteropOpResponsePayload
        .builder()
        .build();
  }

  @Override
  public InteropOpResponsePayload createVariant() {
    return InteropOpResponsePayload
        .builder()
        .build();
  }
}