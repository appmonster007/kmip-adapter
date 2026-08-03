package org.purplebean.kmip.codec.ttlv.model.core.type;

import java.math.BigInteger;
import org.junit.jupiter.api.DisplayName;
import org.purplebean.kmip.model.core.type.PrimeExponentP;
import org.purplebean.kmip.test.suite.AbstractTtlvSerializationTestSuite;

@DisplayName("PrimeExponentP TTLV Serialization Tests")
class PrimeExponentPTtlvTest extends AbstractTtlvSerializationTestSuite<PrimeExponentP> {

  @Override
  public Class<PrimeExponentP> type() {
    return PrimeExponentP.class;
  }

  @Override
  public PrimeExponentP createDefault() {
    return PrimeExponentP
        .builder()
        .value(BigInteger.valueOf(65537))
        .build();
  }

  @Override
  public PrimeExponentP createVariant() {
    return PrimeExponentP
        .builder()
        .value(BigInteger.valueOf(3))
        .build();
  }
}