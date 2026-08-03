package org.purplebean.kmip.model.core.type;

import org.junit.jupiter.api.DisplayName;
import org.purplebean.kmip.api.EncodingType;
import org.purplebean.kmip.test.suite.AbstractKmipDataTypeTestSuite;

@DisplayName("TagLength Domain Tests")
class TagLengthTest extends AbstractKmipDataTypeTestSuite<TagLength> {

  @Override
  protected Class<TagLength> type() {
    return TagLength.class;
  }

  @Override
  protected TagLength createDefault() {
    return TagLength.of(128);
  }

  @Override
  protected EncodingType expectedEncodingType() {
    return EncodingType.INTEGER;
  }
}