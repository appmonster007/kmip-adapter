package org.purplebean.kmip.codec.json.model.core.type;

import java.math.BigInteger;
import org.junit.jupiter.api.DisplayName;
import org.purplebean.kmip.model.core.type.G;
import org.purplebean.kmip.test.suite.AbstractJsonSerializationTestSuite;

@DisplayName("G JSON Serialization Tests")
class GJsonTest extends AbstractJsonSerializationTestSuite<G> {

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