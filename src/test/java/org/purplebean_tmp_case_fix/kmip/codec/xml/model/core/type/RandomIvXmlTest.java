package org.purplebean.kmip.codec.xml.model.core.type;

import org.junit.jupiter.api.DisplayName;
import org.purplebean.kmip.model.core.type.RandomIv;
import org.purplebean.kmip.test.suite.AbstractXmlSerializationTestSuite;

@DisplayName("RandomIv XML Serialization Tests")
class RandomIvXmlTest extends AbstractXmlSerializationTestSuite<RandomIv> {

  @Override
  public Class<RandomIv> type() {
    return RandomIv.class;
  }

  @Override
  public RandomIv createDefault() {
    return RandomIv.of(true);
  }

  @Override
  public RandomIv createVariant() {
    return RandomIv.of(false);
  }
}