package org.purpleBean.kmip.codec.ttlv.model.v2_1.structure;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.model.v2_1.structure.ObjectGroups;
import org.purpleBean.kmip.test.suite.AbstractTtlvSerializationTestSuite;

@DisplayName("ObjectGroups Ttlv Serialization Tests")
class ObjectGroupsTtlvTest extends AbstractTtlvSerializationTestSuite<ObjectGroups> {

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