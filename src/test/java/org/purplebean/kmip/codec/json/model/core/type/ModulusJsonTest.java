package org.purplebean.kmip.codec.json.model.core.type;

import java.math.BigInteger;
import org.junit.jupiter.api.DisplayName;
import org.purplebean.kmip.model.core.type.Modulus;
import org.purplebean.kmip.test.suite.AbstractJsonSerializationTestSuite;

@DisplayName("Modulus JSON Serialization Tests")
class ModulusJsonTest extends AbstractJsonSerializationTestSuite<Modulus> {

  @Override
  public Class<Modulus> type() {
    return Modulus.class;
  }

  @Override
  public Modulus createDefault() {
    return Modulus
        .builder()
        .value(BigInteger.TEN)
        .build();
  }

  @Override
  public Modulus createVariant() {
    return Modulus
        .builder()
        .value(BigInteger.valueOf(20))
        .build();
  }
}