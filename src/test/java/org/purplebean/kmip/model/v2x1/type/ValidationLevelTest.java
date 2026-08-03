package org.purplebean.kmip.model.v2x1.type;

import org.junit.jupiter.api.DisplayName;
import org.purplebean.kmip.api.EncodingType;
import org.purplebean.kmip.api.KmipSpec;
import org.purplebean.kmip.test.suite.AbstractKmipDataTypeTestSuite;

@DisplayName("ValidationLevel Domain Tests")
class ValidationLevelTest extends AbstractKmipDataTypeTestSuite<ValidationLevel> {

  @Override
  protected void setupDefaultSpec() {
    defaultSpec = KmipSpec.UnknownVersion;
  }

  @Override
  protected Class<ValidationLevel> type() {
    return ValidationLevel.class;
  }

  @Override
  protected ValidationLevel createDefault() {
    return ValidationLevel.of(123);
  }

  @Override
  protected EncodingType expectedEncodingType() {
    return EncodingType.INTEGER;
  }
}