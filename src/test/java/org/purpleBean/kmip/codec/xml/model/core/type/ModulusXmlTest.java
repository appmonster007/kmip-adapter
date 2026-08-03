package org.purpleBean.kmip.codec.xml.model.core.type;

import java.math.BigInteger;
import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.model.core.type.Modulus;
import org.purpleBean.kmip.test.suite.AbstractXmlSerializationTestSuite;

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