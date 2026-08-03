package org.purplebean.kmip.model.core.type;

import org.junit.jupiter.api.DisplayName;
import org.purplebean.kmip.api.EncodingType;
import org.purplebean.kmip.test.suite.AbstractKmipDataTypeTestSuite;

@DisplayName("DataLength Domain Tests")
class DataLengthTest extends AbstractKmipDataTypeTestSuite<DataLength> {

  @Override
  protected Class<DataLength> type() {
    return DataLength.class;
  }

  @Override
  protected DataLength createDefault() {
    return DataLength.of(128);
  }

  @Override
  protected EncodingType expectedEncodingType() {
    return EncodingType.INTEGER;
  }
}
