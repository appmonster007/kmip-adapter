package org.purplebean.kmip.codec.xml.model.v3x0.structure.link;

import org.junit.jupiter.api.DisplayName;
import org.purplebean.kmip.model.v3x0.structure.link.GroupLink;
import org.purplebean.kmip.test.suite.AbstractXmlSerializationTestSuite;

@DisplayName("GroupLink Xml Serialization Tests")
class GroupLinkXmlTest extends AbstractXmlSerializationTestSuite<GroupLink> {

  @Override
  public Class<GroupLink> type() {
    return GroupLink.class;
  }

  @Override
  public GroupLink createDefault() {
    return GroupLink.of("test-id");
  }

  @Override
  public GroupLink createVariant() {
    return GroupLink.of("test-id-2");
  }
}
