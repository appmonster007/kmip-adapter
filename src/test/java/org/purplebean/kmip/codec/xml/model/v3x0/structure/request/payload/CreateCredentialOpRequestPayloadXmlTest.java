package org.purplebean.kmip.codec.xml.model.v3x0.structure.request.payload;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.purplebean.kmip.api.KmipSpec;
import org.purplebean.kmip.model.core.enumeration.CredentialType;
import org.purplebean.kmip.model.core.structure.UsernameAndPassword;
import org.purplebean.kmip.model.v2x1.structure.Attributes;
import org.purplebean.kmip.model.v3x0.structure.request.payload.CreateCredentialOpRequestPayload;
import org.purplebean.kmip.test.suite.AbstractXmlSerializationTestSuite;

@DisplayName("CreateCredentialOpRequestPayload Xml Serialization Tests")
class CreateCredentialOpRequestPayloadXmlTest
    extends AbstractXmlSerializationTestSuite<CreateCredentialOpRequestPayload> {

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
        .attributes(Attributes.of(List.of()))
        .credentialValue(UsernameAndPassword.of("user", "pass"))
        .build();
  }

  @Override
  public CreateCredentialOpRequestPayload createVariant() {
    return CreateCredentialOpRequestPayload
        .builder()
        .credentialType(CredentialType.Standard.USERNAME_AND_PASSWORD.inst())
        .attributes(Attributes.of(List.of()))
        .credentialValue(UsernameAndPassword.of("admin", "secret"))
        .build();
  }
}