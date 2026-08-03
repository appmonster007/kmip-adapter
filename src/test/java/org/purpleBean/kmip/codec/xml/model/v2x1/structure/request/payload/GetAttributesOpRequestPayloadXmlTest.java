package org.purplebean.kmip.codec.xml.model.v2x1.structure.request.payload;

import org.junit.jupiter.api.DisplayName;
import org.purplebean.kmip.model.v2x1.structure.request.payload.GetAttributesOpRequestPayload;
import org.purplebean.kmip.test.suite.AbstractXmlSerializationTestSuite;

@DisplayName("GetAttributesOpRequestPayload Xml Serialization Tests")
class GetAttributesOpRequestPayloadXmlTest
    extends AbstractXmlSerializationTestSuite<GetAttributesOpRequestPayload> {

  @Override
  public Class<GetAttributesOpRequestPayload> type() {
    return GetAttributesOpRequestPayload.class;
  }

  @Override
  public GetAttributesOpRequestPayload createDefault() {
    return GetAttributesOpRequestPayload
        .builder()
        .build();
  }

  @Override
  public GetAttributesOpRequestPayload createVariant() {
    return GetAttributesOpRequestPayload
        .builder()
        .build();
  }
}