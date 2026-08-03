package org.purpleBean.kmip.codec.ttlv.model.core.type;

import java.math.BigInteger;
import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.model.core.type.PublicExponent;
import org.purpleBean.kmip.test.suite.AbstractTtlvSerializationTestSuite;

@DisplayName("PublicExponent TTLV Serialization Tests")
class PublicExponentTtlvTest extends AbstractTtlvSerializationTestSuite<PublicExponent> {

  @Override
  public Class<PublicExponent> type() {
    return PublicExponent.class;
  }

  @Override
  public PublicExponent createDefault() {
    return PublicExponent
        .builder()
        .value(BigInteger.valueOf(65537))
        .build();
  }

  @Override
  public PublicExponent createVariant() {
    return PublicExponent
        .builder()
        .value(BigInteger.valueOf(3))
        .build();
  }
}