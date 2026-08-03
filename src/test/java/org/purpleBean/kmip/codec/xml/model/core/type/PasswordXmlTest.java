package org.purpleBean.kmip.codec.xml.model.core.type;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.model.core.type.Password;
import org.purpleBean.kmip.test.suite.AbstractXmlSerializationTestSuite;

@DisplayName("Password XML Serialization Tests")
class PasswordXmlTest extends AbstractXmlSerializationTestSuite<Password> {

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