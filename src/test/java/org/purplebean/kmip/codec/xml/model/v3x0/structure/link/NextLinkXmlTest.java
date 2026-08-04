package org.purplebean.kmip.codec.xml.model.v3x0.structure.link;

import org.junit.jupiter.api.DisplayName;
import org.purplebean.kmip.model.v3x0.structure.link.NextLink;
import org.purplebean.kmip.test.suite.AbstractXmlSerializationTestSuite;

@DisplayName("NextLink Xml Serialization Tests")
class NextLinkXmlTest extends AbstractXmlSerializationTestSuite<NextLink> {

  @Override
  public Class<NextLink> type() {
    return NextLink.class;
  }

  @Override
  public NextLink createDefault() {
    return NextLink.of("test-id");
  }

  @Override
  public NextLink createVariant() {
    return NextLink.of("test-id");
  }
}