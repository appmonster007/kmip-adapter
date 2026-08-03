package org.purplebean.kmip.model.core.type;

import org.junit.jupiter.api.DisplayName;
import org.purplebean.kmip.api.EncodingType;
import org.purplebean.kmip.test.suite.AbstractKmipDataTypeTestSuite;

@DisplayName("BatchOrderOption Domain Tests")
class BatchOrderOptionTest extends AbstractKmipDataTypeTestSuite<BatchOrderOption> {

  @Override
  protected Class<BatchOrderOption> type() {
    return BatchOrderOption.class;
  }

  @Override
  protected BatchOrderOption createDefault() {
    return BatchOrderOption
        .builder()
        .value(true)
        .build();
  }

  @Override
  protected EncodingType expectedEncodingType() {
    return EncodingType.BOOLEAN;
  }
}