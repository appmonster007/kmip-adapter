package org.purplebean.kmip.codec.ttlv.model.core.type;

import org.junit.jupiter.api.DisplayName;
import org.purplebean.kmip.model.core.type.ObjectGroup;
import org.purplebean.kmip.test.suite.AbstractTtlvSerializationTestSuite;

@DisplayName("ObjectGroup TTLV Serialization Tests")
class ObjectGroupTtlvTest extends AbstractTtlvSerializationTestSuite<ObjectGroup> {

  @Override
  public Class<ObjectGroup> type() {
    return ObjectGroup.class;
  }

  @Override
  public ObjectGroup createDefault() {
    return ObjectGroup
        .builder()
        .value("test")
        .build();
  }

  @Override
  public ObjectGroup createVariant() {
    return ObjectGroup
        .builder()
        .value("test-2")
        .build();
  }
}
