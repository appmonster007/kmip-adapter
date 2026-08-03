package org.purplebean.kmip.codec.json.model.v2x1.type;

import org.junit.jupiter.api.DisplayName;
import org.purplebean.kmip.model.v2x1.type.ValidationProfile;
import org.purplebean.kmip.test.suite.AbstractJsonSerializationTestSuite;

@DisplayName("ValidationProfile Json Serialization Tests")
class ValidationProfileJsonTest extends AbstractJsonSerializationTestSuite<ValidationProfile> {

  @Override
  public Class<ValidationProfile> type() {
    return ValidationProfile.class;
  }

  @Override
  public ValidationProfile createDefault() {
    return ValidationProfile.of("default-string");
  }

  @Override
  public ValidationProfile createVariant() {
    return ValidationProfile.of("variant-string");
  }
}