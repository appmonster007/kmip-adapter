package org.purplebean.kmip.codec.json.model.core.type;

import java.math.BigInteger;
import org.junit.jupiter.api.DisplayName;
import org.purplebean.kmip.model.core.type.PrimeFieldSize;
import org.purplebean.kmip.test.suite.AbstractJsonSerializationTestSuite;

@DisplayName("PrimeFieldSize JSON Serialization Tests")
class PrimeFieldSizeJsonTest extends AbstractJsonSerializationTestSuite<PrimeFieldSize> {

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