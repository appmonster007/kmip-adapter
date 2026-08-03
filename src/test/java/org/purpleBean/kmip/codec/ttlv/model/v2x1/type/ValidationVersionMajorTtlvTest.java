package org.purplebean.kmip.codec.ttlv.model.v2x1.type;

import org.junit.jupiter.api.DisplayName;
import org.purplebean.kmip.model.v2x1.type.ValidationVersionMajor;
import org.purplebean.kmip.test.suite.AbstractTtlvSerializationTestSuite;

@DisplayName("ValidationVersionMajor Ttlv Serialization Tests")
class ValidationVersionMajorTtlvTest
    extends AbstractTtlvSerializationTestSuite<ValidationVersionMajor> {

  @Override
  public Class<ValidationVersionMajor> type() {
    return ValidationVersionMajor.class;
  }

  @Override
  public ValidationVersionMajor createDefault() {
    return ValidationVersionMajor.of(123);
  }

  @Override
  public ValidationVersionMajor createVariant() {
    return ValidationVersionMajor.of(456);
  }
}