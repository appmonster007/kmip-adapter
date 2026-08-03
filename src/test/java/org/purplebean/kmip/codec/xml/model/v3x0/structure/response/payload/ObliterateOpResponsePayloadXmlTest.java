package org.purplebean.kmip.codec.xml.model.v3x0.structure.response.payload;

import org.junit.jupiter.api.DisplayName;
import org.purplebean.kmip.model.v3x0.structure.response.payload.ObliterateOpResponsePayload;
import org.purplebean.kmip.test.suite.AbstractXmlSerializationTestSuite;

@DisplayName("ObliterateOpResponsePayload Xml Serialization Tests")
class ObliterateOpResponsePayloadXmlTest
    extends AbstractXmlSerializationTestSuite<ObliterateOpResponsePayload> {

  @Override
  public Class<ObliterateOpResponsePayload> type() {
    return ObliterateOpResponsePayload.class;
  }

  @Override
  public ObliterateOpResponsePayload createDefault() {
    return ObliterateOpResponsePayload
        .builder()
        .build();
  }

  @Override
  public ObliterateOpResponsePayload createVariant() {
    return ObliterateOpResponsePayload
        .builder()
        .build();
  }
}