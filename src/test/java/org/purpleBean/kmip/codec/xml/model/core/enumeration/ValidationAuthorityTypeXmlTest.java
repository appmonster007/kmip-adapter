package org.purpleBean.kmip.codec.xml.model.core.enumeration;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.model.core.enumeration.ValidationAuthorityType;
import org.purpleBean.kmip.test.suite.AbstractXmlSerializationTestSuite;

@DisplayName("ValidationAuthorityType XML Serialization")
class ValidationAuthorityTypeXmlTest
    extends AbstractXmlSerializationTestSuite<ValidationAuthorityType> {
  @Override
  public Class<ValidationAuthorityType> type() {
    return ValidationAuthorityType.class;
  }

  @Override
  public ValidationAuthorityType createDefault() {
    return ValidationAuthorityType.Standard.UNSPECIFIED.inst();
  }

  @Override
  public ValidationAuthorityType createVariant() {
    return ValidationAuthorityType.Standard.NIST_CMVP.inst();
  }
}
