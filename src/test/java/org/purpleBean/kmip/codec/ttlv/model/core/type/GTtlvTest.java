package org.purpleBean.kmip.codec.ttlv.model.core.type;

import java.math.BigInteger;
import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.model.core.type.G;
import org.purpleBean.kmip.test.suite.AbstractTtlvSerializationTestSuite;

@DisplayName("G TTLV Serialization Tests")
class GTtlvTest extends AbstractTtlvSerializationTestSuite<G> {

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