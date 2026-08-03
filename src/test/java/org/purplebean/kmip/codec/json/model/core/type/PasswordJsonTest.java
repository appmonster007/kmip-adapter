package org.purplebean.kmip.codec.json.model.core.type;

import org.junit.jupiter.api.DisplayName;
import org.purplebean.kmip.model.core.type.Password;
import org.purplebean.kmip.test.suite.AbstractJsonSerializationTestSuite;

@DisplayName("Password JSON Serialization Tests")
class PasswordJsonTest extends AbstractJsonSerializationTestSuite<Password> {

  @Override
  public Class<Password> type() {
    return Password.class;
  }

  @Override
  public Password createDefault() {
    return Password
        .builder()
        .value("test-password")
        .build();
  }

  @Override
  public Password createVariant() {
    return Password
        .builder()
        .value("another-password")
        .build();
  }
}