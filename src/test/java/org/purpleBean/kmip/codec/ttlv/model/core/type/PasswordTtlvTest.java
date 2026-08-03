package org.purpleBean.kmip.codec.ttlv.model.core.type;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.model.core.type.Password;
import org.purpleBean.kmip.test.suite.AbstractTtlvSerializationTestSuite;

@DisplayName("Password TTLV Serialization Tests")
class PasswordTtlvTest extends AbstractTtlvSerializationTestSuite<Password> {

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