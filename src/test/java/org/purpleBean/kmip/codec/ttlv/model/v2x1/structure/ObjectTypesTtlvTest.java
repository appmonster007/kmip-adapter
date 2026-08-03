package org.purpleBean.kmip.codec.ttlv.model.v2x1.structure;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.model.v2x1.structure.ObjectTypes;
import org.purpleBean.kmip.test.suite.AbstractTtlvSerializationTestSuite;

@DisplayName("ObjectTypes Ttlv Serialization Tests")
class ObjectTypesTtlvTest extends AbstractTtlvSerializationTestSuite<ObjectTypes> {

  @Override
  public Class<ObjectTypes> type() {
    return ObjectTypes.class;
  }

  @Override
  public ObjectTypes createDefault() {
    return ObjectTypes
        .builder()
        .build();
  }

  @Override
  public ObjectTypes createVariant() {
    return ObjectTypes
        .builder()
        .build();
  }
}