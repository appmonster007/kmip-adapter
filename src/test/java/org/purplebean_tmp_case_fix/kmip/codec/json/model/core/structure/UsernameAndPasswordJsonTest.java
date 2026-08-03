package org.purplebean.kmip.codec.json.model.core.structure;

import org.junit.jupiter.api.DisplayName;
import org.purplebean.kmip.api.KmipSpec;
import org.purplebean.kmip.model.core.structure.UsernameAndPassword;
import org.purplebean.kmip.model.core.type.Password;
import org.purplebean.kmip.model.core.type.Username;
import org.purplebean.kmip.test.suite.AbstractJsonSerializationTestSuite;

@DisplayName("UsernameAndPassword Json Serialization Tests")
class UsernameAndPasswordJsonTest extends AbstractJsonSerializationTestSuite<UsernameAndPassword> {

  @Override
  protected void setupDefaultSpec() {
    defaultSpec = KmipSpec.UnknownVersion;
  }

  @Override
  public Class<UsernameAndPassword> type() {
    return UsernameAndPassword.class;
  }

  @Override
  public UsernameAndPassword createDefault() {
    return UsernameAndPassword
        .builder()
        .username(Username.of("test-user"))
        .password(Password.of("test-password"))
        .build();
  }

  @Override
  public UsernameAndPassword createVariant() {
    return UsernameAndPassword
        .builder()
        .username(Username.of("test-user-variant"))
        .password(Password.of("test-password-variant"))
        .build();
  }
}