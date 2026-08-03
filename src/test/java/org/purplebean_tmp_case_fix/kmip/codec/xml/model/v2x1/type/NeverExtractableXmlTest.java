package org.purplebean.kmip.codec.xml.model.v2x1.type;

import org.junit.jupiter.api.DisplayName;
import org.purplebean.kmip.model.v2x1.type.NeverExtractable;
import org.purplebean.kmip.test.suite.AbstractXmlSerializationTestSuite;

@DisplayName("NeverExtractable Xml Serialization Tests")
class NeverExtractableXmlTest extends AbstractXmlSerializationTestSuite<NeverExtractable> {

  @Override
  public Class<NeverExtractable> type() {
    return NeverExtractable.class;
  }

  @Override
  public NeverExtractable createDefault() {
    return NeverExtractable.of(true);
  }

  @Override
  public NeverExtractable createVariant() {
    return NeverExtractable.of(false);
  }
}