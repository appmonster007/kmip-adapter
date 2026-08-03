package org.purplebean.kmip.codec.xml.model.core.type;

import java.math.BigInteger;
import org.junit.jupiter.api.DisplayName;
import org.purplebean.kmip.model.core.type.P;
import org.purplebean.kmip.test.suite.AbstractXmlSerializationTestSuite;

@DisplayName("P XML Serialization Tests")
class PXmlTest extends AbstractXmlSerializationTestSuite<P> {

  @Override
  public Class<P> type() {
    return P.class;
  }

  @Override
  public P createDefault() {
    return P
        .builder()
        .value(BigInteger.ONE)
        .build();
  }

  @Override
  public P createVariant() {
    return P
        .builder()
        .value(BigInteger.TEN)
        .build();
  }
}