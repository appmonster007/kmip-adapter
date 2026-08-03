package org.purpleBean.kmip.codec.xml.model.v3_0.structure.response.payload;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.model.v3_0.structure.response.payload.ObliterateOpResponsePayload;
import org.purpleBean.kmip.test.suite.AbstractXmlSerializationTestSuite;

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