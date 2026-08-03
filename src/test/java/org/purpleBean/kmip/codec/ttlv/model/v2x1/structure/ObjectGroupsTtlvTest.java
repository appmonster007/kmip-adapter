package org.purplebean.kmip.codec.ttlv.model.v2x1.structure;

import org.junit.jupiter.api.DisplayName;
import org.purplebean.kmip.model.v2x1.structure.ObjectGroups;
import org.purplebean.kmip.test.suite.AbstractTtlvSerializationTestSuite;

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