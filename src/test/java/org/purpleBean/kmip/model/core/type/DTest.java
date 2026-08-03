package org.purpleBean.kmip.model.core.type;

import java.math.BigInteger;
import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.api.EncodingType;
import org.purpleBean.kmip.test.suite.AbstractKmipDataTypeTestSuite;

@DisplayName("D Domain Tests")
class DTest extends AbstractKmipDataTypeTestSuite<D> {

  @Override
  protected Class<D> type() {
    return D.class;
  }

  @Override
  protected D createDefault() {
    return D
        .builder()
        .value(BigInteger.ONE)
        .build();
  }

  @Override
  protected EncodingType expectedEncodingType() {
    return EncodingType.BIG_INTEGER;
  }
}