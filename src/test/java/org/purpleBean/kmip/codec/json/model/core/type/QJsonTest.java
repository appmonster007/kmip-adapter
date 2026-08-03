package org.purplebean.kmip.codec.json.model.core.type;

import java.math.BigInteger;
import org.junit.jupiter.api.DisplayName;
import org.purplebean.kmip.model.core.type.Q;
import org.purplebean.kmip.test.suite.AbstractJsonSerializationTestSuite;

@DisplayName("Q JSON Serialization Tests")
class QJsonTest extends AbstractJsonSerializationTestSuite<Q> {

  @Override
  public Class<Q> type() {
    return Q.class;
  }

  @Override
  public Q createDefault() {
    return Q
        .builder()
        .value(BigInteger.ONE)
        .build();
  }

  @Override
  public Q createVariant() {
    return Q
        .builder()
        .value(BigInteger.TEN)
        .build();
  }
}