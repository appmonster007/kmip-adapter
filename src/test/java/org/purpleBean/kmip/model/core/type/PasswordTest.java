package org.purplebean.kmip.model.core.type;

import org.junit.jupiter.api.DisplayName;
import org.purplebean.kmip.api.EncodingType;
import org.purplebean.kmip.test.suite.AbstractKmipDataTypeTestSuite;

@DisplayName("Password Domain Tests")
class PasswordTest extends AbstractKmipDataTypeTestSuite<Password> {

  @Override
  protected Class<Password> type() {
    return Password.class;
  }

  @Override
  protected Password createDefault() {
    return Password
        .builder()
        .value("test-password")
        .build();
  }

  @Override
  protected EncodingType expectedEncodingType() {
    return EncodingType.TEXT_STRING;
  }
}