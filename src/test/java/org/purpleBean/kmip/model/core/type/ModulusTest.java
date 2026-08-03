package org.purplebean.kmip.model.core.type;

import java.math.BigInteger;
import org.junit.jupiter.api.DisplayName;
import org.purplebean.kmip.api.EncodingType;
import org.purplebean.kmip.test.suite.AbstractKmipDataTypeTestSuite;

@DisplayName("Modulus Domain Tests")
class ModulusTest extends AbstractKmipDataTypeTestSuite<Modulus> {

  @Override
  protected Class<Modulus> type() {
    return Modulus.class;
  }

  @Override
  protected Modulus createDefault() {
    return Modulus
        .builder()
        .value(BigInteger.TEN)
        .build();
  }

  @Override
  protected EncodingType expectedEncodingType() {
    return EncodingType.BIG_INTEGER;
  }
}