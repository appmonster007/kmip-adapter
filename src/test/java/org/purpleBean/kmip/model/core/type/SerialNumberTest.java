package org.purpleBean.kmip.model.core.type;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.api.EncodingType;
import org.purpleBean.kmip.test.suite.AbstractKmipDataTypeTestSuite;

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