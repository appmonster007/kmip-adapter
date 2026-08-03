package org.purpleBean.kmip.codec.xml.model.v2x1.type;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.model.v2x1.type.ReplaceExisting;
import org.purpleBean.kmip.test.suite.AbstractXmlSerializationTestSuite;

@DisplayName("ReplaceExisting Xml Serialization Tests")
class ReplaceExistingXmlTest extends AbstractXmlSerializationTestSuite<ReplaceExisting> {

  @Override
  public Class<ReplaceExisting> type() {
    return ReplaceExisting.class;
  }

  @Override
  public ReplaceExisting createDefault() {
    return ReplaceExisting.of(true);
  }

  @Override
  public ReplaceExisting createVariant() {
    return ReplaceExisting.of(false);
  }
}