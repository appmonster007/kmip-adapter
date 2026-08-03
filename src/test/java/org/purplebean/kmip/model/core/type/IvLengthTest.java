package org.purplebean.kmip.model.core.type;

import org.junit.jupiter.api.DisplayName;
import org.purplebean.kmip.api.EncodingType;
import org.purplebean.kmip.test.suite.AbstractKmipDataTypeTestSuite;

@DisplayName("IvLength Domain Tests")
class IvLengthTest extends AbstractKmipDataTypeTestSuite<IvLength> {

  @Override
  protected Class<IvLength> type() {
    return IvLength.class;
  }

  @Override
  protected IvLength createDefault() {
    return IvLength.of(128);
  }

  @Override
  protected EncodingType expectedEncodingType() {
    return EncodingType.INTEGER;
  }
}