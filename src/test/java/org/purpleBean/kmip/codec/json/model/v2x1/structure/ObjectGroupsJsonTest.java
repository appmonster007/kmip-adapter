package org.purpleBean.kmip.codec.json.model.v2x1.structure;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.model.v2x1.structure.ObjectGroups;
import org.purpleBean.kmip.test.suite.AbstractJsonSerializationTestSuite;

@DisplayName("ObjectGroups Json Serialization Tests")
class ObjectGroupsJsonTest extends AbstractJsonSerializationTestSuite<ObjectGroups> {

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