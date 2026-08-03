package org.purplebean.kmip.model.core.type;

import java.math.BigInteger;
import org.junit.jupiter.api.DisplayName;
import org.purplebean.kmip.api.EncodingType;
import org.purplebean.kmip.test.suite.AbstractKmipDataTypeTestSuite;

@DisplayName("PrimeExponentP Domain Tests")
class PrimeExponentPTest extends AbstractKmipDataTypeTestSuite<PrimeExponentP> {

  @Override
  protected Class<PrimeExponentP> type() {
    return PrimeExponentP.class;
  }

  @Override
  protected PrimeExponentP createDefault() {
    return PrimeExponentP
        .builder()
        .value(BigInteger.valueOf(65537))
        .build();
  }

  @Override
  protected EncodingType expectedEncodingType() {
    return EncodingType.BIG_INTEGER;
  }
}