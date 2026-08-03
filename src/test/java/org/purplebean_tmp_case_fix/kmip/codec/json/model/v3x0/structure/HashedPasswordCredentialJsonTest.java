package org.purplebean.kmip.codec.json.model.v3x0.structure;

import org.junit.jupiter.api.DisplayName;
import org.purplebean.kmip.model.v3x0.structure.HashedPasswordCredential;
import org.purplebean.kmip.model.v3x0.type.HashedPasswordUsername;
import org.purplebean.kmip.model.v3x0.type.HashedUsernamePassword;
import org.purplebean.kmip.test.suite.AbstractJsonSerializationTestSuite;

@DisplayName("HashedPasswordCredential Json Serialization Tests")
class HashedPasswordCredentialJsonTest
    extends AbstractJsonSerializationTestSuite<HashedPasswordCredential> {

  @Override
  public Class<HashedPasswordCredential> type() {
    return HashedPasswordCredential.class;
  }

  @Override
  public HashedPasswordCredential createDefault() {
    return HashedPasswordCredential
        .builder()
        .hashedUsernamePassword(HashedUsernamePassword.of(new byte[] {0x01, 0x02, 0x03}))
        .hashedPasswordUsername(HashedPasswordUsername.of(new byte[] {0x04, 0x05, 0x06}))
        .build();
  }

  @Override
  public HashedPasswordCredential createVariant() {
    return HashedPasswordCredential
        .builder()
        .hashedUsernamePassword(HashedUsernamePassword.of(new byte[] {0x07, 0x08, 0x09}))
        .hashedPasswordUsername(HashedPasswordUsername.of(new byte[] {0x0A, 0x0B, 0x0C}))
        .build();
  }
}