package org.purplebean.kmip.codec.xml.model.v1x2.structure.response.payload;

import org.junit.jupiter.api.DisplayName;
import org.purplebean.kmip.model.v1x2.structure.response.payload.PollOpResponsePayload;
import org.purplebean.kmip.test.suite.AbstractXmlSerializationTestSuite;

@DisplayName("PollOpResponsePayload Xml Serialization Tests")
class PollOpResponsePayloadXmlTest
    extends AbstractXmlSerializationTestSuite<PollOpResponsePayload> {

  @Override
  public Class<PollOpResponsePayload> type() {
    return PollOpResponsePayload.class;
  }

  @Override
  public PollOpResponsePayload createDefault() {
    return PollOpResponsePayload
        .builder()
        .build();
  }

  @Override
  public PollOpResponsePayload createVariant() {
    return PollOpResponsePayload
        .builder()
        .build();
  }
}