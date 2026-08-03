package org.purpleBean.kmip.codec.ttlv.model.v2_1.type;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.model.v2_1.type.ValidationVersionMinor;
import org.purpleBean.kmip.test.suite.AbstractTtlvSerializationTestSuite;

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