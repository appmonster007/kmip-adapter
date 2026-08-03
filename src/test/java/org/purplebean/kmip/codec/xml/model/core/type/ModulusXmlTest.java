package org.purplebean.kmip.codec.xml.model.core.type;

import java.math.BigInteger;
import org.junit.jupiter.api.DisplayName;
import org.purplebean.kmip.model.core.type.Modulus;
import org.purplebean.kmip.test.suite.AbstractXmlSerializationTestSuite;

@DisplayName("Modulus XML Serialization Tests")
class ModulusXmlTest extends AbstractXmlSerializationTestSuite<Modulus> {

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