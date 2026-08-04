package org.purplebean.kmip.model.core.type;

import org.junit.jupiter.api.DisplayName;
import org.purplebean.kmip.api.EncodingType;
import org.purplebean.kmip.test.suite.AbstractKmipDataTypeTestSuite;

@DisplayName("SaltLength Domain Tests")
class SaltLengthTest extends AbstractKmipDataTypeTestSuite<SaltLength> {

  @Override
  protected Class<SaltLength> type() {
    return SaltLength.class;
  }

  @Override
  protected SaltLength createDefault() {
    return SaltLength.of(123);
  }

  @Override
  protected EncodingType expectedEncodingType() {
    return EncodingType.INTEGER;
  }
}
