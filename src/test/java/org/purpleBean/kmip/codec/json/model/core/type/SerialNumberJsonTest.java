package org.purpleBean.kmip.codec.json.model.core.type;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.model.core.type.SerialNumber;
import org.purpleBean.kmip.test.suite.AbstractJsonSerializationTestSuite;

@DisplayName("SerialNumber JSON Serialization Tests")
class SerialNumberJsonTest extends AbstractJsonSerializationTestSuite<SerialNumber> {

  @Override
  public Class<SerialNumber> type() {
    return SerialNumber.class;
  }

  @Override
  public SerialNumber createDefault() {
    return SerialNumber
        .builder()
        .value("12345")
        .build();
  }

  @Override
  public SerialNumber createVariant() {
    return SerialNumber
        .builder()
        .value("67890")
        .build();
  }
}