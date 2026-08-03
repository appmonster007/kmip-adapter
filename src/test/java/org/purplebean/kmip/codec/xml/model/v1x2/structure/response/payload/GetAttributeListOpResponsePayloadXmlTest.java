package org.purplebean.kmip.codec.xml.model.v1x2.structure.response.payload;

import org.junit.jupiter.api.DisplayName;
import org.purplebean.kmip.api.KmipSpec;
import org.purplebean.kmip.model.core.type.AttributeName;
import org.purplebean.kmip.model.core.type.UniqueIdentifier;
import org.purplebean.kmip.model.v1x2.structure.response.payload.GetAttributeListOpResponsePayload;
import org.purplebean.kmip.test.suite.AbstractXmlSerializationTestSuite;

@DisplayName("GetAttributeListOpResponsePayload Xml Serialization Tests")
class GetAttributeListOpResponsePayloadXmlTest
    extends AbstractXmlSerializationTestSuite<GetAttributeListOpResponsePayload> {

  @Override
  protected void setupDefaultSpec() {
    defaultSpec = KmipSpec.V1_2;
  }

  @Override
  public Class<GetAttributeListOpResponsePayload> type() {
    return GetAttributeListOpResponsePayload.class;
  }

  @Override
  public GetAttributeListOpResponsePayload createDefault() {
    return GetAttributeListOpResponsePayload
        .builder()
        .uniqueIdentifier(UniqueIdentifier.of("123e4567-e89b-12d3-a456-426614174000"))
        .attributeName(AttributeName.of("Attribute1"))
        .build();
  }

  @Override
  public GetAttributeListOpResponsePayload createVariant() {
    return GetAttributeListOpResponsePayload
        .builder()
        .uniqueIdentifier(UniqueIdentifier.of("123e4567-e89b-12d3-a456-426614174001"))
        .attributeName(AttributeName.of("AttributeA"))
        .attributeName(AttributeName.of("AttributeB"))
        .build();
  }
}
