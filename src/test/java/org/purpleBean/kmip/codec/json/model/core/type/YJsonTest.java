package org.purplebean.kmip.codec.json.model.core.type;

import java.math.BigInteger;
import org.junit.jupiter.api.DisplayName;
import org.purplebean.kmip.model.core.type.Y;
import org.purplebean.kmip.test.suite.AbstractJsonSerializationTestSuite;

@DisplayName("Y JSON Serialization Tests")
class YJsonTest extends AbstractJsonSerializationTestSuite<Y> {

  @Override
  public Class<Y> type() {
    return Y.class;
  }

  @Override
  public Y createDefault() {
    return Y
        .builder()
        .value(BigInteger.ONE)
        .build();
  }

  @Override
  public Y createVariant() {
    return Y
        .builder()
        .value(BigInteger.TEN)
        .build();
  }
}