package org.purplebean.kmip.codec.xml.model.v2x1.type;

import org.junit.jupiter.api.DisplayName;
import org.purplebean.kmip.model.v2x1.type.RotateOffset;
import org.purplebean.kmip.test.suite.AbstractXmlSerializationTestSuite;

@DisplayName("RotateOffset Xml Serialization Tests")
class RotateOffsetXmlTest extends AbstractXmlSerializationTestSuite<RotateOffset> {

  @Override
  public Class<RotateOffset> type() {
    return RotateOffset.class;
  }

  @Override
  public RotateOffset createDefault() {
    return RotateOffset.of(12345L);
  }

  @Override
  public RotateOffset createVariant() {
    return RotateOffset.of(54321L);
  }
}