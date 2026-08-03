package org.purplebean.kmip.codec.xml.model.core.type;

import java.math.BigInteger;
import org.junit.jupiter.api.DisplayName;
import org.purplebean.kmip.model.core.type.J;
import org.purplebean.kmip.test.suite.AbstractXmlSerializationTestSuite;

@DisplayName("J XML Serialization Tests")
class JXmlTest extends AbstractXmlSerializationTestSuite<J> {

  @Override
  public Class<J> type() {
    return J.class;
  }

  @Override
  public J createDefault() {
    return J
        .builder()
        .value(BigInteger.ONE)
        .build();
  }

  @Override
  public J createVariant() {
    return J
        .builder()
        .value(BigInteger.TEN)
        .build();
  }
}