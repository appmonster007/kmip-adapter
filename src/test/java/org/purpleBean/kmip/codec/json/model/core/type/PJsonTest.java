package org.purpleBean.kmip.codec.json.model.core.type;

import java.math.BigInteger;
import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.model.core.type.P;
import org.purpleBean.kmip.test.suite.AbstractJsonSerializationTestSuite;

@DisplayName("P JSON Serialization Tests")
class PJsonTest extends AbstractJsonSerializationTestSuite<P> {

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