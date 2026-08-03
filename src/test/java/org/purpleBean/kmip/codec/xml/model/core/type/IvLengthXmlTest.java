package org.purpleBean.kmip.codec.xml.model.core.type;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.model.core.type.IvLength;
import org.purpleBean.kmip.test.suite.AbstractXmlSerializationTestSuite;

@DisplayName("IvLength XML Serialization Tests")
class IvLengthXmlTest extends AbstractXmlSerializationTestSuite<IvLength> {

  @Override
  public Class<IvLength> type() {
    return IvLength.class;
  }

  @Override
  public IvLength createDefault() {
    return IvLength.of(128);
  }

  @Override
  public IvLength createVariant() {
    return IvLength.of(256);
  }
}