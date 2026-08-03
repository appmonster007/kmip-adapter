package org.purpleBean.kmip.codec.xml.model.v2_1.type;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.model.v2_1.type.ValidationVendorUri;
import org.purpleBean.kmip.test.suite.AbstractXmlSerializationTestSuite;

@DisplayName("ValidationVendorUri Xml Serialization Tests")
class ValidationVendorUriXmlTest extends AbstractXmlSerializationTestSuite<ValidationVendorUri> {

  @Override
  public Class<ValidationVendorUri> type() {
    return ValidationVendorUri.class;
  }

  @Override
  public ValidationVendorUri createDefault() {
    return ValidationVendorUri.of("default-string");
  }

  @Override
  public ValidationVendorUri createVariant() {
    return ValidationVendorUri.of("variant-string");
  }
}