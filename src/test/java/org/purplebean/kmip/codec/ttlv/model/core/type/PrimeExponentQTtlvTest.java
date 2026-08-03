package org.purplebean.kmip.codec.ttlv.model.core.type;

import java.math.BigInteger;
import org.junit.jupiter.api.DisplayName;
import org.purplebean.kmip.model.core.type.PrimeExponentQ;
import org.purplebean.kmip.test.suite.AbstractTtlvSerializationTestSuite;

@DisplayName("PrimeExponentQ TTLV Serialization Tests")
class PrimeExponentQTtlvTest extends AbstractTtlvSerializationTestSuite<PrimeExponentQ> {

  @Override
  public Class<PrimeExponentQ> type() {
    return PrimeExponentQ.class;
  }

  @Override
  public PrimeExponentQ createDefault() {
    return PrimeExponentQ
        .builder()
        .value(BigInteger.valueOf(65537))
        .build();
  }

  @Override
  public PrimeExponentQ createVariant() {
    return PrimeExponentQ
        .builder()
        .value(BigInteger.valueOf(3))
        .build();
  }
}