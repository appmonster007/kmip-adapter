package org.purplebean.kmip.codec.xml.model.v2x1.type;

import org.junit.jupiter.api.DisplayName;
import org.purplebean.kmip.model.v2x1.type.ValidationVendorUri;
import org.purplebean.kmip.test.suite.AbstractXmlSerializationTestSuite;

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