package org.purplebean.kmip.codec.xml.model.core.type;

import java.math.BigInteger;
import org.junit.jupiter.api.DisplayName;
import org.purplebean.kmip.model.core.type.PrimeExponentP;
import org.purplebean.kmip.test.suite.AbstractXmlSerializationTestSuite;

@DisplayName("PrimeExponentP XML Serialization Tests")
class PrimeExponentPXmlTest extends AbstractXmlSerializationTestSuite<PrimeExponentP> {

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