package org.purpleBean.kmip.codec.xml.model.v3x0.structure.response.payload;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.model.core.type.UniqueIdentifier;
import org.purpleBean.kmip.model.v3x0.structure.response.payload.CreateUserOpResponsePayload;
import org.purpleBean.kmip.test.suite.AbstractXmlSerializationTestSuite;

@DisplayName("CreateUserOpResponsePayload Xml Serialization Tests")
class CreateUserOpResponsePayloadXmlTest
    extends AbstractXmlSerializationTestSuite<CreateUserOpResponsePayload> {

  @Override
  public Class<CreateUserOpResponsePayload> type() {
    return CreateUserOpResponsePayload.class;
  }

  @Override
  public CreateUserOpResponsePayload createDefault() {
    return CreateUserOpResponsePayload
        .builder()
        .uniqueIdentifier(UniqueIdentifier
            .builder()
            .value("test-uid-1")
            .build())
        .build();
  }

  @Override
  public CreateUserOpResponsePayload createVariant() {
    return CreateUserOpResponsePayload
        .builder()
        .uniqueIdentifier(UniqueIdentifier
            .builder()
            .value("test-uid-2")
            .build())
        .build();
  }
}