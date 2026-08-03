package org.purplebean.kmip.codec.xml.model.v1x2.structure.response.payload;

import org.junit.jupiter.api.DisplayName;
import org.purplebean.kmip.api.KmipSpec;
import org.purplebean.kmip.model.core.structure.Attribute;
import org.purplebean.kmip.model.core.type.AttributeName;
import org.purplebean.kmip.model.core.type.AttributeValue;
import org.purplebean.kmip.model.core.type.UniqueIdentifier;
import org.purplebean.kmip.model.v1x2.structure.response.payload.ModifyAttributeOpResponsePayload;
import org.purplebean.kmip.test.suite.AbstractXmlSerializationTestSuite;

@DisplayName("ModifyAttributeOpResponsePayload Xml Serialization Tests")
class ModifyAttributeOpResponsePayloadXmlTest
    extends AbstractXmlSerializationTestSuite<ModifyAttributeOpResponsePayload> {

  @Override
  protected void setupDefaultSpec() {
    defaultSpec = KmipSpec.V1_2;
  }

  @Override
  public Class<ModifyAttributeOpResponsePayload> type() {
    return ModifyAttributeOpResponsePayload.class;
  }

  @Override
  public ModifyAttributeOpResponsePayload createDefault() {
    return ModifyAttributeOpResponsePayload
        .builder()
        .uniqueIdentifier(UniqueIdentifier.of("123e4567-e89b-12d3-a456-426614174000"))
        .attribute(Attribute.of(AttributeName.of("test-attribute"),
            AttributeValue.ofTextString("test-value")))
        .build();
  }

  @Override
  public ModifyAttributeOpResponsePayload createVariant() {
    return ModifyAttributeOpResponsePayload
        .builder()
        .uniqueIdentifier(UniqueIdentifier.of("123e4567-e89b-12d3-a456-426614174001"))
        .attribute(Attribute.of(AttributeName.of("variant-attribute"),
            AttributeValue.ofTextString("variant-value")))
        .build();
  }
}
