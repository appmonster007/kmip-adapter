package org.purplebean.kmip.codec.ttlv.model.core.type;

import java.math.BigInteger;
import org.junit.jupiter.api.DisplayName;
import org.purplebean.kmip.model.core.type.X;
import org.purplebean.kmip.test.suite.AbstractTtlvSerializationTestSuite;

@DisplayName("X TTLV Serialization Tests")
class XTtlvTest extends AbstractTtlvSerializationTestSuite<X> {

  @Override
  public Class<X> type() {
    return X.class;
  }

  @Override
  public X createDefault() {
    return X
        .builder()
        .value(BigInteger.ONE)
        .build();
  }

  @Override
  public X createVariant() {
    return X
        .builder()
        .value(BigInteger.TEN)
        .build();
  }
}