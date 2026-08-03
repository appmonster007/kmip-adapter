package org.purpleBean.kmip.codec.json.model.v3x0.structure.request.payload;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.model.core.enumeration.CredentialType;
import org.purpleBean.kmip.model.core.structure.UsernameAndPassword;
import org.purpleBean.kmip.model.v3x0.structure.request.payload.CreateCredentialOpRequestPayload;
import org.purpleBean.kmip.test.suite.AbstractJsonSerializationTestSuite;

@DisplayName("CreateCredentialOpRequestPayload Json Serialization Tests")
class CreateCredentialOpRequestPayloadJsonTest
    extends AbstractJsonSerializationTestSuite<CreateCredentialOpRequestPayload> {

  @Override
  public Class<CreateCredentialOpRequestPayload> type() {
    return CreateCredentialOpRequestPayload.class;
  }

  @Override
  protected void setupDefaultSpec() {
    defaultSpec = KmipSpec.V3_0;
  }


  @Override
  public CreateCredentialOpRequestPayload createDefault() {
    return CreateCredentialOpRequestPayload
        .builder()
        .credentialType(CredentialType.Standard.USERNAME_AND_PASSWORD.inst())
        .credentialValue(UsernameAndPassword.of("user", "pass"))
        .build();
  }

  @Override
  public CreateCredentialOpRequestPayload createVariant() {
    return CreateCredentialOpRequestPayload
        .builder()
        .credentialType(CredentialType.Standard.USERNAME_AND_PASSWORD.inst())
        .credentialValue(UsernameAndPassword.of("admin", "secret"))
        .build();
  }
}