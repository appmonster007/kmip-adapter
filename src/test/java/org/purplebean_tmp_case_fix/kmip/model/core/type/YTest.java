package org.purplebean.kmip.model.core.type;

import java.math.BigInteger;
import org.junit.jupiter.api.DisplayName;
import org.purplebean.kmip.api.EncodingType;
import org.purplebean.kmip.test.suite.AbstractKmipDataTypeTestSuite;

@DisplayName("Y Domain Tests")
class YTest extends AbstractKmipDataTypeTestSuite<Y> {

  @Override
  protected Class<Y> type() {
    return Y.class;
  }

  @Override
  protected Y createDefault() {
    return Y
        .builder()
        .value(BigInteger.ONE)
        .build();
  }

  @Override
  protected EncodingType expectedEncodingType() {
    return EncodingType.BIG_INTEGER;
  }
}