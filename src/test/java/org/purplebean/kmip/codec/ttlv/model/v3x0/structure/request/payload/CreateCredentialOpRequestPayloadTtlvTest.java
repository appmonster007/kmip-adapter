package org.purplebean.kmip.codec.ttlv.model.v3x0.structure.request.payload;

import org.junit.jupiter.api.DisplayName;
import org.purplebean.kmip.api.KmipSpec;
import org.purplebean.kmip.model.core.enumeration.CredentialType;
import org.purplebean.kmip.model.core.structure.UsernameAndPassword;
import org.purplebean.kmip.model.v3x0.structure.request.payload.CreateCredentialOpRequestPayload;
import org.purplebean.kmip.test.suite.AbstractTtlvSerializationTestSuite;

@DisplayName("CreateCredentialOpRequestPayload Ttlv Serialization Tests")
class CreateCredentialOpRequestPayloadTtlvTest
    extends AbstractTtlvSerializationTestSuite<CreateCredentialOpRequestPayload> {

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