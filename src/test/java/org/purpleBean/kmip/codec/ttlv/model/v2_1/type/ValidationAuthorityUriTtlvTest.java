package org.purpleBean.kmip.codec.ttlv.model.v2_1.type;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.model.v2_1.type.ValidationAuthorityUri;
import org.purpleBean.kmip.test.suite.AbstractTtlvSerializationTestSuite;

@DisplayName("ValidationAuthorityUri Ttlv Serialization Tests")
class ValidationAuthorityUriTtlvTest
    extends AbstractTtlvSerializationTestSuite<ValidationAuthorityUri> {

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