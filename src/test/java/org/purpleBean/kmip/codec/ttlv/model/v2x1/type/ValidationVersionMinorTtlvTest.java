package org.purplebean.kmip.codec.ttlv.model.v2x1.type;

import org.junit.jupiter.api.DisplayName;
import org.purplebean.kmip.model.v2x1.type.ValidationVersionMinor;
import org.purplebean.kmip.test.suite.AbstractTtlvSerializationTestSuite;

@DisplayName("ValidationVersionMinor Ttlv Serialization Tests")
class ValidationVersionMinorTtlvTest
    extends AbstractTtlvSerializationTestSuite<ValidationVersionMinor> {

  @Override
  public Class<ValidationVersionMinor> type() {
    return ValidationVersionMinor.class;
  }

  @Override
  public ValidationVersionMinor createDefault() {
    return ValidationVersionMinor.of(123);
  }

  @Override
  public ValidationVersionMinor createVariant() {
    return ValidationVersionMinor.of(456);
  }
}