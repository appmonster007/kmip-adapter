package org.purplebean.kmip.codec.xml.model.v2x1.structure.response.payload;

import org.junit.jupiter.api.DisplayName;
import org.purplebean.kmip.model.core.type.UniqueIdentifier;
import org.purplebean.kmip.model.v2x1.structure.response.payload.AddAttributeOpResponsePayload;
import org.purplebean.kmip.test.suite.AbstractXmlSerializationTestSuite;

@DisplayName("AddAttributeOpResponsePayload Xml Serialization Tests")
class AddAttributeOpResponsePayloadXmlTest
    extends AbstractXmlSerializationTestSuite<AddAttributeOpResponsePayload> {

  @Override
  public Class<AddAttributeOpResponsePayload> type() {
    return AddAttributeOpResponsePayload.class;
  }

  @Override
  public AddAttributeOpResponsePayload createDefault() {
    return AddAttributeOpResponsePayload
        .builder()
        .uniqueIdentifier(UniqueIdentifier.of("test-uid"))
        .build();
  }

  @Override
  public AddAttributeOpResponsePayload createVariant() {
    return AddAttributeOpResponsePayload
        .builder()
        .uniqueIdentifier(UniqueIdentifier.of("other-uid"))
        .build();
  }
}