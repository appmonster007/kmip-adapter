package org.purpleBean.kmip.codec.xml.model.v3x0.structure.request.payload;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.model.v2x1.structure.Attributes;
import org.purpleBean.kmip.model.v3x0.structure.request.payload.CreateGroupOpRequestPayload;
import org.purpleBean.kmip.test.suite.AbstractXmlSerializationTestSuite;

@DisplayName("CreateGroupOpRequestPayload Xml Serialization Tests")
class CreateGroupOpRequestPayloadXmlTest
    extends AbstractXmlSerializationTestSuite<CreateGroupOpRequestPayload> {

  @Override
  public Class<CreateGroupOpRequestPayload> type() {
    return CreateGroupOpRequestPayload.class;
  }

  @Override
  public CreateGroupOpRequestPayload createDefault() {
    return CreateGroupOpRequestPayload
        .builder()
        .attributes(Attributes.of(java.util.List.of()))
        .build();
  }

  @Override
  public CreateGroupOpRequestPayload createVariant() {
    return CreateGroupOpRequestPayload
        .builder()
        .attributes(Attributes.of(java.util.List.of()))
        .build();
  }
}