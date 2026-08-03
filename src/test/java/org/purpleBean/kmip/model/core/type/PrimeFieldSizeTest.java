package org.purpleBean.kmip.model.core.type;

import java.math.BigInteger;
import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.api.EncodingType;
import org.purpleBean.kmip.test.suite.AbstractKmipDataTypeTestSuite;

@DisplayName("PrimeFieldSize Domain Tests")
class PrimeFieldSizeTest extends AbstractKmipDataTypeTestSuite<PrimeFieldSize> {

  @Override
  protected Class<PrimeFieldSize> type() {
    return PrimeFieldSize.class;
  }

  @Override
  protected PrimeFieldSize createDefault() {
    return PrimeFieldSize
        .builder()
        .value(BigInteger.valueOf(2048))
        .build();
  }

  @Override
  protected EncodingType expectedEncodingType() {
    return EncodingType.BIG_INTEGER;
  }
}