package org.purpleBean.kmip.codec.xml.model.v2x1.type;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.model.v2x1.type.FinalIndicator;
import org.purpleBean.kmip.test.suite.AbstractXmlSerializationTestSuite;

@DisplayName("FinalIndicator Xml Serialization Tests")
class FinalIndicatorXmlTest extends AbstractXmlSerializationTestSuite<FinalIndicator> {

  @Override
  public Class<FinalIndicator> type() {
    return FinalIndicator.class;
  }

  @Override
  public FinalIndicator createDefault() {
    return FinalIndicator.of(true);
  }

  @Override
  public FinalIndicator createVariant() {
    return FinalIndicator.of(false);
  }
}