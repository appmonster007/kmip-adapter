package org.purplebean.kmip.codec.ttlv.model.core.enumeration;

import org.junit.jupiter.api.DisplayName;
import org.purplebean.kmip.model.core.enumeration.ValidationAuthorityType;
import org.purplebean.kmip.test.suite.AbstractTtlvSerializationTestSuite;

@DisplayName("ValidationAuthorityType TTLV Serialization")
class ValidationAuthorityTypeTtlvTest
    extends AbstractTtlvSerializationTestSuite<ValidationAuthorityType> {
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
