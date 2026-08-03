package org.purplebean.kmip.model.core.type;

import java.math.BigInteger;
import org.junit.jupiter.api.DisplayName;
import org.purplebean.kmip.api.EncodingType;
import org.purplebean.kmip.test.suite.AbstractKmipDataTypeTestSuite;

@DisplayName("G Domain Tests")
class GTest extends AbstractKmipDataTypeTestSuite<G> {

  @Override
  protected Class<G> type() {
    return G.class;
  }

  @Override
  protected G createDefault() {
    return G
        .builder()
        .value(BigInteger.ONE)
        .build();
  }

  @Override
  protected EncodingType expectedEncodingType() {
    return EncodingType.BIG_INTEGER;
  }
}