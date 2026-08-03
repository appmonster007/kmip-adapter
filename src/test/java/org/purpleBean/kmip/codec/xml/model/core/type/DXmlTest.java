package org.purplebean.kmip.codec.xml.model.core.type;

import java.math.BigInteger;
import org.junit.jupiter.api.DisplayName;
import org.purplebean.kmip.model.core.type.D;
import org.purplebean.kmip.test.suite.AbstractXmlSerializationTestSuite;

@DisplayName("D XML Serialization Tests")
class DXmlTest extends AbstractXmlSerializationTestSuite<D> {

  @Override
  public Class<D> type() {
    return D.class;
  }

  @Override
  public D createDefault() {
    return D
        .builder()
        .value(BigInteger.ONE)
        .build();
  }

  @Override
  public D createVariant() {
    return D
        .builder()
        .value(BigInteger.TEN)
        .build();
  }
}