package org.purplebean.kmip.model.core.type;

import org.junit.jupiter.api.DisplayName;
import org.purplebean.kmip.api.EncodingType;
import org.purplebean.kmip.test.suite.AbstractKmipDataTypeTestSuite;

@DisplayName("SerialNumber Domain Tests")
class SerialNumberTest extends AbstractKmipDataTypeTestSuite<SerialNumber> {

  @Override
  protected Class<SerialNumber> type() {
    return SerialNumber.class;
  }

  @Override
  protected SerialNumber createDefault() {
    return SerialNumber
        .builder()
        .value("12345")
        .build();
  }

  @Override
  protected EncodingType expectedEncodingType() {
    return EncodingType.TEXT_STRING;
  }
}