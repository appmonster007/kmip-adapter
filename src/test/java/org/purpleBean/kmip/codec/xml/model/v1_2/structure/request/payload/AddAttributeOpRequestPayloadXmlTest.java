package org.purpleBean.kmip.codec.xml.model.v1_2.structure.request.payload;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.model.core.structure.Attribute;
import org.purpleBean.kmip.model.core.type.AttributeName;
import org.purpleBean.kmip.model.core.type.AttributeValue;
import org.purpleBean.kmip.model.core.type.UniqueIdentifier;
import org.purpleBean.kmip.model.v1_2.structure.request.payload.AddAttributeOpRequestPayload;
import org.purpleBean.kmip.test.suite.AbstractXmlSerializationTestSuite;

@DisplayName("AddAttributeOpRequestPayload Xml Serialization Tests")
class AddAttributeOpRequestPayloadXmlTest
    extends AbstractXmlSerializationTestSuite<AddAttributeOpRequestPayload> {

  @Override
  protected void setupDefaultSpec() {
    defaultSpec = KmipSpec.V1_2;
  }

  @Override
  public Class<AddAttributeOpRequestPayload> type() {
    return AddAttributeOpRequestPayload.class;
  }

  @Override
  public AddAttributeOpRequestPayload createDefault() {
    return AddAttributeOpRequestPayload
        .builder()
        .uniqueIdentifier(UniqueIdentifier.of("123e4567-e89b-12d3-a456-426614174000"))
        .attribute(Attribute.of(AttributeName.of("test-attribute"),
            AttributeValue.ofTextString("test-value")))
        .build();
  }

  @Override
  public AddAttributeOpRequestPayload createVariant() {
    return AddAttributeOpRequestPayload
        .builder()
        .uniqueIdentifier(UniqueIdentifier.of("123e4567-e89b-12d3-a456-426614174001"))
        .attribute(Attribute.of(AttributeName.of("variant-attribute"),
            AttributeValue.ofTextString("variant-value")))
        .build();
  }
}
