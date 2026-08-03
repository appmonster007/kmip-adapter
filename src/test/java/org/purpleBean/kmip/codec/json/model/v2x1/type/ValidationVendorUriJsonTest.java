package org.purpleBean.kmip.codec.json.model.v2x1.type;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.model.v2x1.type.ValidationVendorUri;
import org.purpleBean.kmip.test.suite.AbstractJsonSerializationTestSuite;

@DisplayName("ValidationVendorUri Json Serialization Tests")
class ValidationVendorUriJsonTest extends AbstractJsonSerializationTestSuite<ValidationVendorUri> {

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