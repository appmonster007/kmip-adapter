package org.purpleBean.kmip.codec.xml.model.v1x2.structure.request.payload;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.model.core.structure.Attribute;
import org.purpleBean.kmip.model.core.type.AttributeName;
import org.purpleBean.kmip.model.core.type.AttributeValue;
import org.purpleBean.kmip.model.core.type.UniqueIdentifier;
import org.purpleBean.kmip.model.v1x2.structure.request.payload.ModifyAttributeOpRequestPayload;
import org.purpleBean.kmip.test.suite.AbstractXmlSerializationTestSuite;

@DisplayName("ModifyAttributeOpRequestPayload Xml Serialization Tests")
class ModifyAttributeOpRequestPayloadXmlTest
    extends AbstractXmlSerializationTestSuite<ModifyAttributeOpRequestPayload> {

  @Override
  protected void setupDefaultSpec() {
    defaultSpec = KmipSpec.V1_2;
  }

  @Override
  public Class<ModifyAttributeOpRequestPayload> type() {
    return ModifyAttributeOpRequestPayload.class;
  }

  @Override
  public ModifyAttributeOpRequestPayload createDefault() {
    return ModifyAttributeOpRequestPayload
        .builder()
        .uniqueIdentifier(UniqueIdentifier.of("123e4567-e89b-12d3-a456-426614174000"))
        .attribute(Attribute.of(AttributeName.of("test-attribute"),
            AttributeValue.ofTextString("test-value")))
        .build();
  }

  @Override
  public ModifyAttributeOpRequestPayload createVariant() {
    return ModifyAttributeOpRequestPayload
        .builder()
        .uniqueIdentifier(UniqueIdentifier.of("123e4567-e89b-12d3-a456-426614174001"))
        .attribute(Attribute.of(AttributeName.of("variant-attribute"),
            AttributeValue.ofTextString("variant-value")))
        .build();
  }
}
