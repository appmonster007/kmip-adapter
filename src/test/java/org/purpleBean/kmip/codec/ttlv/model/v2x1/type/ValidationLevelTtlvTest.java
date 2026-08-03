package org.purplebean.kmip.codec.ttlv.model.v2x1.type;

import org.junit.jupiter.api.DisplayName;
import org.purplebean.kmip.model.v2x1.type.ValidationLevel;
import org.purplebean.kmip.test.suite.AbstractTtlvSerializationTestSuite;

@DisplayName("ValidationLevel Ttlv Serialization Tests")
class ValidationLevelTtlvTest extends AbstractTtlvSerializationTestSuite<ValidationLevel> {

  @Override
  public Class<ValidationLevel> type() {
    return ValidationLevel.class;
  }

  @Override
  public ValidationLevel createDefault() {
    return ValidationLevel.of(123);
  }

  @Override
  public ValidationLevel createVariant() {
    return ValidationLevel.of(456);
  }
}