package org.purpleBean.kmip.codec.xml.model.core.type;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.model.core.type.BatchOrderOption;
import org.purpleBean.kmip.test.suite.AbstractXmlSerializationTestSuite;

@DisplayName("BatchOrderOption XML Serialization Tests")
class BatchOrderOptionXmlTest extends AbstractXmlSerializationTestSuite<BatchOrderOption> {

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