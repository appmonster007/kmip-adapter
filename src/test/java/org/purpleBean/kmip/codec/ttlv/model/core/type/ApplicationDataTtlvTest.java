package org.purplebean.kmip.codec.ttlv.model.core.type;

import org.junit.jupiter.api.DisplayName;
import org.purplebean.kmip.model.core.type.ApplicationData;
import org.purplebean.kmip.test.suite.AbstractTtlvSerializationTestSuite;

@DisplayName("ApplicationData TTLV Serialization Tests")
class ApplicationDataTtlvTest extends AbstractTtlvSerializationTestSuite<ApplicationData> {

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