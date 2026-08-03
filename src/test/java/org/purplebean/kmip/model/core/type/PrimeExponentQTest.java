package org.purplebean.kmip.model.core.type;

import java.math.BigInteger;
import org.junit.jupiter.api.DisplayName;
import org.purplebean.kmip.api.EncodingType;
import org.purplebean.kmip.test.suite.AbstractKmipDataTypeTestSuite;

@DisplayName("PrimeExponentQ Domain Tests")
class PrimeExponentQTest extends AbstractKmipDataTypeTestSuite<PrimeExponentQ> {

  @Override
  protected Class<PrimeExponentQ> type() {
    return PrimeExponentQ.class;
  }

  @Override
  protected PrimeExponentQ createDefault() {
    return PrimeExponentQ
        .builder()
        .value(BigInteger.valueOf(65537))
        .build();
  }

  @Override
  protected EncodingType expectedEncodingType() {
    return EncodingType.BIG_INTEGER;
  }
}