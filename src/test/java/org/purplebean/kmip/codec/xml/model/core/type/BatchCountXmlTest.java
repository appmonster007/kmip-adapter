package org.purplebean.kmip.codec.xml.model.core.type;

import org.junit.jupiter.api.DisplayName;
import org.purplebean.kmip.model.core.type.BatchCount;
import org.purplebean.kmip.test.suite.AbstractXmlSerializationTestSuite;

@DisplayName("BatchCount XML Serialization Tests")
class BatchCountXmlTest extends AbstractXmlSerializationTestSuite<BatchCount> {

  @Override
  public Class<BatchCount> type() {
    return BatchCount.class;
  }

  @Override
  public BatchCount createDefault() {
    return BatchCount
        .builder()
        .value(5)
        .build();
  }

  @Override
  public BatchCount createVariant() {
    return BatchCount
        .builder()
        .value(10)
        .build();
  }
}