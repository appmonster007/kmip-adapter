package org.purpleBean.kmip.codec.xml.model.v2_1.type;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.model.v2_1.type.Extractable;
import org.purpleBean.kmip.test.suite.AbstractXmlSerializationTestSuite;

@DisplayName("Extractable Xml Serialization Tests")
class ExtractableXmlTest extends AbstractXmlSerializationTestSuite<Extractable> {

  @Override
  public Class<Extractable> type() {
    return Extractable.class;
  }

  @Override
  public Extractable createDefault() {
    return Extractable.of(true);
  }

  @Override
  public Extractable createVariant() {
    return Extractable.of(false);
  }
}