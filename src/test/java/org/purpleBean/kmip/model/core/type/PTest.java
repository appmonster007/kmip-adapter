package org.purplebean.kmip.model.core.type;

import java.math.BigInteger;
import org.junit.jupiter.api.DisplayName;
import org.purplebean.kmip.api.EncodingType;
import org.purplebean.kmip.test.suite.AbstractKmipDataTypeTestSuite;

@DisplayName("P Domain Tests")
class PTest extends AbstractKmipDataTypeTestSuite<P> {

  @Override
  protected Class<P> type() {
    return P.class;
  }

  @Override
  protected P createDefault() {
    return P
        .builder()
        .value(BigInteger.ONE)
        .build();
  }

  @Override
  protected EncodingType expectedEncodingType() {
    return EncodingType.BIG_INTEGER;
  }
}