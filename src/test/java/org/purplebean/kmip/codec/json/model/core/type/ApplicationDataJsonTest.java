package org.purplebean.kmip.codec.json.model.core.type;

import org.junit.jupiter.api.DisplayName;
import org.purplebean.kmip.model.core.type.ApplicationData;
import org.purplebean.kmip.test.suite.AbstractJsonSerializationTestSuite;

@DisplayName("ApplicationData JSON Serialization Tests")
class ApplicationDataJsonTest extends AbstractJsonSerializationTestSuite<ApplicationData> {

  @Override
  public Class<ApplicationData> type() {
    return ApplicationData.class;
  }

  @Override
  public ApplicationData createDefault() {
    return ApplicationData
        .builder()
        .value("test-data")
        .build();
  }

  @Override
  public ApplicationData createVariant() {
    return ApplicationData
        .builder()
        .value("another-data")
        .build();
  }
}