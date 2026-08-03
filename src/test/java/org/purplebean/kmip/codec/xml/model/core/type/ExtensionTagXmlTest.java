package org.purplebean.kmip.codec.xml.model.core.type;

import org.junit.jupiter.api.DisplayName;
import org.purplebean.kmip.model.core.type.ExtensionTag;
import org.purplebean.kmip.test.suite.AbstractXmlSerializationTestSuite;

@DisplayName("ExtensionTag XML Serialization Tests")
class ExtensionTagXmlTest extends AbstractXmlSerializationTestSuite<ExtensionTag> {

  @Override
  public Class<ExtensionTag> type() {
    return ExtensionTag.class;
  }

  @Override
  public ExtensionTag createDefault() {
    return ExtensionTag
        .builder()
        .value(1)
        .build();
  }

  @Override
  public ExtensionTag createVariant() {
    return ExtensionTag
        .builder()
        .value(2)
        .build();
  }
}