package org.purpleBean.kmip.model.core.type;

import java.math.BigInteger;
import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.api.EncodingType;
import org.purpleBean.kmip.test.suite.AbstractKmipDataTypeTestSuite;

@DisplayName("PrivateExponent Domain Tests")
class PrivateExponentTest extends AbstractKmipDataTypeTestSuite<PrivateExponent> {

  @Override
  protected Class<PrivateExponent> type() {
    return PrivateExponent.class;
  }

  @Override
  protected PrivateExponent createDefault() {
    return PrivateExponent
        .builder()
        .value(BigInteger.valueOf(12345))
        .build();
  }

  @Override
  protected EncodingType expectedEncodingType() {
    return EncodingType.BIG_INTEGER;
  }
}