package org.purpleBean.kmip.codec.json.model.v3x0.structure.response.payload;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.model.core.type.UniqueIdentifier;
import org.purpleBean.kmip.model.v3x0.structure.response.payload.CreateCredentialOpResponsePayload;
import org.purpleBean.kmip.test.suite.AbstractJsonSerializationTestSuite;

@DisplayName("CreateCredentialOpResponsePayload Json Serialization Tests")
class CreateCredentialOpResponsePayloadJsonTest
    extends AbstractJsonSerializationTestSuite<CreateCredentialOpResponsePayload> {

  @Override
  public Class<CreateCredentialOpResponsePayload> type() {
    return CreateCredentialOpResponsePayload.class;
  }

  @Override
  public CreateCredentialOpResponsePayload createDefault() {
    return CreateCredentialOpResponsePayload
        .builder()
        .uniqueIdentifier(UniqueIdentifier
            .builder()
            .value("test-uid-1")
            .build())
        .build();
  }

  @Override
  public CreateCredentialOpResponsePayload createVariant() {
    return CreateCredentialOpResponsePayload
        .builder()
        .uniqueIdentifier(UniqueIdentifier
            .builder()
            .value("test-uid-2")
            .build())
        .build();
  }
}