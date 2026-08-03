package org.purplebean.kmip.codec.xml.model.v2x1.type;

import org.junit.jupiter.api.DisplayName;
import org.purplebean.kmip.model.v2x1.type.RotateLatest;
import org.purplebean.kmip.test.suite.AbstractXmlSerializationTestSuite;

@DisplayName("RotateLatest Xml Serialization Tests")
class RotateLatestXmlTest extends AbstractXmlSerializationTestSuite<RotateLatest> {

  @Override
  public Class<RotateLatest> type() {
    return RotateLatest.class;
  }

  @Override
  public RotateLatest createDefault() {
    return RotateLatest.of(true);
  }

  @Override
  public RotateLatest createVariant() {
    return RotateLatest.of(false);
  }
}