package org.purpleBean.kmip.codec.ttlv.model.core.type;

import java.math.BigInteger;
import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.model.core.type.PrimeFieldSize;
import org.purpleBean.kmip.test.suite.AbstractTtlvSerializationTestSuite;

@DisplayName("PrimeFieldSize TTLV Serialization Tests")
class PrimeFieldSizeTtlvTest extends AbstractTtlvSerializationTestSuite<PrimeFieldSize> {

  @Override
  public Class<PrimeFieldSize> type() {
    return PrimeFieldSize.class;
  }

  @Override
  public PrimeFieldSize createDefault() {
    return PrimeFieldSize
        .builder()
        .value(BigInteger.valueOf(2048))
        .build();
  }

  @Override
  public PrimeFieldSize createVariant() {
    return PrimeFieldSize
        .builder()
        .value(BigInteger.valueOf(3072))
        .build();
  }
}