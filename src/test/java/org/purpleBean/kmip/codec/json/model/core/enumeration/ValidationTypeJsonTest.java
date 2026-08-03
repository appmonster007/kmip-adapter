package org.purpleBean.kmip.codec.json.model.core.enumeration;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.model.core.enumeration.ValidationType;
import org.purpleBean.kmip.test.suite.AbstractJsonSerializationTestSuite;

@DisplayName("ValidationType JSON Serialization")
class ValidationTypeJsonTest extends AbstractJsonSerializationTestSuite<ValidationType> {
  @Override
  public Class<ValidationType> type() {
    return ValidationType.class;
  }

  @Override
  public ValidationType createDefault() {
    return ValidationType.Standard.UNSPECIFIED.inst();
  }

  @Override
  public ValidationType createVariant() {
    return ValidationType.Standard.HARDWARE.inst();
  }
}
