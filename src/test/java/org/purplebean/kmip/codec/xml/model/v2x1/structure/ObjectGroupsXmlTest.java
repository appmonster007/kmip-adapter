package org.purplebean.kmip.codec.xml.model.v2x1.structure;

import org.junit.jupiter.api.DisplayName;
import org.purplebean.kmip.model.v2x1.structure.ObjectGroups;
import org.purplebean.kmip.test.suite.AbstractXmlSerializationTestSuite;

@DisplayName("ObjectGroups Xml Serialization Tests")
class ObjectGroupsXmlTest extends AbstractXmlSerializationTestSuite<ObjectGroups> {

  @Override
  public Class<ObjectGroups> type() {
    return ObjectGroups.class;
  }

  @Override
  public ObjectGroups createDefault() {
    return ObjectGroups
        .builder()
        .build();
  }

  @Override
  public ObjectGroups createVariant() {
    return ObjectGroups
        .builder()
        .build();
  }
}