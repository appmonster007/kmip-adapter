package org.purpleBean.kmip.codec.xml.model.v3_0.structure.link;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.model.core.type.UniqueIdentifier;
import org.purpleBean.kmip.model.v3_0.structure.link.NextLink;
import org.purpleBean.kmip.test.suite.AbstractXmlSerializationTestSuite;

@DisplayName("NextLink Xml Serialization Tests")
class NextLinkXmlTest extends AbstractXmlSerializationTestSuite<NextLink> {

  @Override
  public Class<NextLink> type() {
    return NextLink.class;
  }

  @Override
  public NextLink createDefault() {
    return NextLink.of(UniqueIdentifier.of("test-id"));
  }

  @Override
  public NextLink createVariant() {
    return NextLink.of(UniqueIdentifier.of("test-id"));
  }
}