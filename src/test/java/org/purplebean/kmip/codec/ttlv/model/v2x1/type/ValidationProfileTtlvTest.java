package org.purplebean.kmip.codec.ttlv.model.v2x1.type;

import org.junit.jupiter.api.DisplayName;
import org.purplebean.kmip.model.v2x1.type.ValidationProfile;
import org.purplebean.kmip.test.suite.AbstractTtlvSerializationTestSuite;

@DisplayName("ValidationProfile Ttlv Serialization Tests")
class ValidationProfileTtlvTest extends AbstractTtlvSerializationTestSuite<ValidationProfile> {

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