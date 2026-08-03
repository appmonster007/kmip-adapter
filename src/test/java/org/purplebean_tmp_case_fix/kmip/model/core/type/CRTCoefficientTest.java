package org.purplebean.kmip.model.core.type;

import java.math.BigInteger;
import org.junit.jupiter.api.DisplayName;
import org.purplebean.kmip.api.EncodingType;
import org.purplebean.kmip.test.suite.AbstractKmipDataTypeTestSuite;

@DisplayName("CRTCoefficient Domain Tests")
class CRTCoefficientTest extends AbstractKmipDataTypeTestSuite<CRTCoefficient> {

  @Override
  protected Class<CRTCoefficient> type() {
    return CRTCoefficient.class;
  }

  @Override
  protected CRTCoefficient createDefault() {
    return CRTCoefficient
        .builder()
        .value(BigInteger.valueOf(12345))
        .build();
  }

  @Override
  protected EncodingType expectedEncodingType() {
    return EncodingType.BIG_INTEGER;
  }
}