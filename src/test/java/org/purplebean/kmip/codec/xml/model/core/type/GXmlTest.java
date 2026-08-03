package org.purplebean.kmip.codec.xml.model.core.type;

import java.math.BigInteger;
import org.junit.jupiter.api.DisplayName;
import org.purplebean.kmip.model.core.type.G;
import org.purplebean.kmip.test.suite.AbstractXmlSerializationTestSuite;

@DisplayName("G XML Serialization Tests")
class GXmlTest extends AbstractXmlSerializationTestSuite<G> {

  @Override
  public Class<G> type() {
    return G.class;
  }

  @Override
  public G createDefault() {
    return G
        .builder()
        .value(BigInteger.ONE)
        .build();
  }

  @Override
  public G createVariant() {
    return G
        .builder()
        .value(BigInteger.TEN)
        .build();
  }
}