package org.purplebean.kmip.codec.xml.model.v2x1.type;

import org.junit.jupiter.api.DisplayName;
import org.purplebean.kmip.model.v2x1.type.Extractable;
import org.purplebean.kmip.test.suite.AbstractXmlSerializationTestSuite;

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