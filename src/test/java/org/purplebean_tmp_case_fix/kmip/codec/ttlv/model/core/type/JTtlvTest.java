package org.purplebean.kmip.codec.ttlv.model.core.type;

import java.math.BigInteger;
import org.junit.jupiter.api.DisplayName;
import org.purplebean.kmip.model.core.type.J;
import org.purplebean.kmip.test.suite.AbstractTtlvSerializationTestSuite;

@DisplayName("J TTLV Serialization Tests")
class JTtlvTest extends AbstractTtlvSerializationTestSuite<J> {

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