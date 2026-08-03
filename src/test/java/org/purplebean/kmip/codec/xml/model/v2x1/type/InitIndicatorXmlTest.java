package org.purplebean.kmip.codec.xml.model.v2x1.type;

import org.junit.jupiter.api.DisplayName;
import org.purplebean.kmip.model.v2x1.type.InitIndicator;
import org.purplebean.kmip.test.suite.AbstractXmlSerializationTestSuite;

@DisplayName("InitIndicator Xml Serialization Tests")
class InitIndicatorXmlTest extends AbstractXmlSerializationTestSuite<InitIndicator> {

  @Override
  public Class<InitIndicator> type() {
    return InitIndicator.class;
  }

  @Override
  public InitIndicator createDefault() {
    return InitIndicator.of(true);
  }

  @Override
  public InitIndicator createVariant() {
    return InitIndicator.of(false);
  }
}