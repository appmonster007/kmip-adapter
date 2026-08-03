package org.purplebean.kmip.codec.json.model.core.enumeration;

import org.junit.jupiter.api.DisplayName;
import org.purplebean.kmip.model.core.enumeration.ValidityIndicator;
import org.purplebean.kmip.test.suite.AbstractJsonSerializationTestSuite;

@DisplayName("ValidityIndicator JSON Serialization")
class ValidityIndicatorJsonTest extends AbstractJsonSerializationTestSuite<ValidityIndicator> {
  @Override
  public Class<ValidityIndicator> type() {
    return ValidityIndicator.class;
  }

  @Override
  public ValidityIndicator createDefault() {
    return ValidityIndicator.Standard.VALID.inst();
  }

  @Override
  public ValidityIndicator createVariant() {
    return ValidityIndicator.Standard.INVALID.inst();
  }
}
