package org.purpleBean.kmip.codec.xml.model.v2x1.type;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.model.v2x1.type.ValidationAuthorityUri;
import org.purpleBean.kmip.test.suite.AbstractXmlSerializationTestSuite;

@DisplayName("ValidationAuthorityUri Xml Serialization Tests")
class ValidationAuthorityUriXmlTest
    extends AbstractXmlSerializationTestSuite<ValidationAuthorityUri> {

  @Override
  public Class<ValidationAuthorityUri> type() {
    return ValidationAuthorityUri.class;
  }

  @Override
  public ValidationAuthorityUri createDefault() {
    return ValidationAuthorityUri.of("default-string");
  }

  @Override
  public ValidationAuthorityUri createVariant() {
    return ValidationAuthorityUri.of("variant-string");
  }
}