package org.purpleBean.kmip.model.v2x1.type;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.api.EncodingType;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.test.suite.AbstractKmipDataTypeTestSuite;


@DisplayName("ValidationVendorUri Domain Tests")
class ValidationVendorUriTest extends AbstractKmipDataTypeTestSuite<ValidationVendorUri> {

  @Override
  protected void setupDefaultSpec() {
    defaultSpec = KmipSpec.UnknownVersion;
  }

  @Override
  protected Class<ValidationVendorUri> type() {
    return ValidationVendorUri.class;
  }

  @Override
  protected ValidationVendorUri createDefault() {
    return ValidationVendorUri.of("default-string");
  }

  @Override
  protected EncodingType expectedEncodingType() {
    return EncodingType.TEXT_STRING;
  }
}