package org.purplebean.kmip.codec.ttlv.model.core.type;

import org.junit.jupiter.api.DisplayName;
import org.purplebean.kmip.model.core.type.BatchOrderOption;
import org.purplebean.kmip.test.suite.AbstractTtlvSerializationTestSuite;

@DisplayName("BatchOrderOption TTLV Serialization Tests")
class BatchOrderOptionTtlvTest extends AbstractTtlvSerializationTestSuite<BatchOrderOption> {

  @Override
  public Class<BatchOrderOption> type() {
    return BatchOrderOption.class;
  }

  @Override
  public BatchOrderOption createDefault() {
    return BatchOrderOption
        .builder()
        .value(true)
        .build();
  }

  @Override
  public BatchOrderOption createVariant() {
    return BatchOrderOption
        .builder()
        .value(false)
        .build();
  }
}