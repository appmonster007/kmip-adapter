package org.purplebean.kmip.model.core.type;

import java.math.BigInteger;
import org.junit.jupiter.api.DisplayName;
import org.purplebean.kmip.api.EncodingType;
import org.purplebean.kmip.test.suite.AbstractKmipDataTypeTestSuite;

@DisplayName("PublicExponent Domain Tests")
class PublicExponentTest extends AbstractKmipDataTypeTestSuite<PublicExponent> {

  @Override
  protected Class<PublicExponent> type() {
    return PublicExponent.class;
  }

  @Override
  protected PublicExponent createDefault() {
    return PublicExponent
        .builder()
        .value(BigInteger.valueOf(65537))
        .build();
  }

  @Override
  protected EncodingType expectedEncodingType() {
    return EncodingType.BIG_INTEGER;
  }
}