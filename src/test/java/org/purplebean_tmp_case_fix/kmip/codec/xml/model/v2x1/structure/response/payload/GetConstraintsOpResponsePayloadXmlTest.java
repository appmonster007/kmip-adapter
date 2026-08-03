package org.purplebean.kmip.codec.xml.model.v2x1.structure.response.payload;

import org.junit.jupiter.api.DisplayName;
import org.purplebean.kmip.model.v2x1.structure.Constraints;
import org.purplebean.kmip.model.v2x1.structure.response.payload.GetConstraintsOpResponsePayload;
import org.purplebean.kmip.test.suite.AbstractXmlSerializationTestSuite;

@DisplayName("GetConstraintsOpResponsePayload Xml Serialization Tests")
class GetConstraintsOpResponsePayloadXmlTest
    extends AbstractXmlSerializationTestSuite<GetConstraintsOpResponsePayload> {

  @Override
  public Class<GetConstraintsOpResponsePayload> type() {
    return GetConstraintsOpResponsePayload.class;
  }

  @Override
  public GetConstraintsOpResponsePayload createDefault() {
    return GetConstraintsOpResponsePayload
        .builder()
        .constraints(Constraints.of(java.util.List.of()))
        .build();
  }

  @Override
  public GetConstraintsOpResponsePayload createVariant() {
    return GetConstraintsOpResponsePayload
        .builder()
        .constraints(Constraints.of(java.util.List.of()))
        .build();
  }
}