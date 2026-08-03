package org.purplebean.kmip.codec.json.model.v1x2.structure.response.payload;

import org.junit.jupiter.api.DisplayName;
import org.purplebean.kmip.api.KmipSpec;
import org.purplebean.kmip.model.core.structure.Attribute;
import org.purplebean.kmip.model.core.type.AttributeName;
import org.purplebean.kmip.model.core.type.AttributeValue;
import org.purplebean.kmip.model.core.type.UniqueIdentifier;
import org.purplebean.kmip.model.v1x2.structure.response.payload.GetAttributesOpResponsePayload;
import org.purplebean.kmip.test.suite.AbstractJsonSerializationTestSuite;

@DisplayName("GetAttributesOpResponsePayload Json Serialization Tests")
class GetAttributesOpResponsePayloadJsonTest
    extends AbstractJsonSerializationTestSuite<GetAttributesOpResponsePayload> {

  @Override
  protected void setupDefaultSpec() {
    defaultSpec = KmipSpec.V1_2;
  }

  @Override
  public Class<GetAttributesOpResponsePayload> type() {
    return GetAttributesOpResponsePayload.class;
  }

  @Override
  public GetAttributesOpResponsePayload createDefault() {
    return GetAttributesOpResponsePayload
        .builder()
        .uniqueIdentifier(UniqueIdentifier.of("123e4567-e89b-12d3-a456-426614174000"))
        .attribute(
            Attribute.of(AttributeName.of("Attribute1"), AttributeValue.ofTextString("Value1")))
        .build();
  }

  @Override
  public GetAttributesOpResponsePayload createVariant() {
    return GetAttributesOpResponsePayload
        .builder()
        .uniqueIdentifier(UniqueIdentifier.of("123e4567-e89b-12d3-a456-426614174001"))
        .attribute(
            Attribute.of(AttributeName.of("AttributeA"), AttributeValue.ofTextString("ValueA")))
        .attribute(
            Attribute.of(AttributeName.of("AttributeB"), AttributeValue.ofTextString("ValueB")))
        .build();
  }
}
