package org.purplebean.kmip.model.core.type;

import org.junit.jupiter.api.DisplayName;
import org.purplebean.kmip.api.EncodingType;
import org.purplebean.kmip.test.suite.AbstractKmipDataTypeTestSuite;

@DisplayName("ApplicationData Domain Tests")
class ApplicationDataTest extends AbstractKmipDataTypeTestSuite<ApplicationData> {

  @Override
  protected Class<ApplicationData> type() {
    return ApplicationData.class;
  }

  @Override
  protected ApplicationData createDefault() {
    return ApplicationData
        .builder()
        .value("test-data")
        .build();
  }

  @Override
  protected EncodingType expectedEncodingType() {
    return EncodingType.TEXT_STRING;
  }
}