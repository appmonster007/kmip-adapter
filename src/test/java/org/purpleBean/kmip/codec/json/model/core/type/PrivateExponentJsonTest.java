package org.purplebean.kmip.codec.json.model.core.type;

import java.math.BigInteger;
import org.junit.jupiter.api.DisplayName;
import org.purplebean.kmip.model.core.type.PrivateExponent;
import org.purplebean.kmip.test.suite.AbstractJsonSerializationTestSuite;

@DisplayName("PrivateExponent JSON Serialization Tests")
class PrivateExponentJsonTest extends AbstractJsonSerializationTestSuite<PrivateExponent> {

  @Override
  public Class<PrivateExponent> type() {
    return PrivateExponent.class;
  }

  @Override
  public PrivateExponent createDefault() {
    return PrivateExponent
        .builder()
        .value(BigInteger.valueOf(12345))
        .build();
  }

  @Override
  public PrivateExponent createVariant() {
    return PrivateExponent
        .builder()
        .value(BigInteger.valueOf(54321))
        .build();
  }
}