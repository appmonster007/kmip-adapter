package org.purplebean.kmip.codec.xml.model.v3x0.structure.link;

import org.junit.jupiter.api.DisplayName;
import org.purplebean.kmip.model.core.type.UniqueIdentifier;
import org.purplebean.kmip.model.v3x0.structure.link.ChildLink;
import org.purplebean.kmip.test.suite.AbstractXmlSerializationTestSuite;

@DisplayName("ChildLink Xml Serialization Tests")
class ChildLinkXmlTest extends AbstractXmlSerializationTestSuite<ChildLink> {

  @Override
  public Class<ChildLink> type() {
    return ChildLink.class;
  }

  @Override
  public ChildLink createDefault() {
    return ChildLink.of(UniqueIdentifier.of("test-id"));
  }

  @Override
  public ChildLink createVariant() {
    return ChildLink.of(UniqueIdentifier.of("test-id"));
  }
}