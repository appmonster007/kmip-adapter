package org.purplebean.kmip.codec.xml.model.core.type;

import org.junit.jupiter.api.DisplayName;
import org.purplebean.kmip.model.core.type.MaximumResponseSize;
import org.purplebean.kmip.test.suite.AbstractXmlSerializationTestSuite;

@DisplayName("MaximumResponseSize XML Serialization Tests")
class MaximumResponseSizeXmlTest extends AbstractXmlSerializationTestSuite<MaximumResponseSize> {

  @Override
  public Class<MaximumResponseSize> type() {
    return MaximumResponseSize.class;
  }

  @Override
  public MaximumResponseSize createDefault() {
    return MaximumResponseSize
        .builder()
        .value(1024)
        .build();
  }

  @Override
  public MaximumResponseSize createVariant() {
    return MaximumResponseSize
        .builder()
        .value(2048)
        .build();
  }
}