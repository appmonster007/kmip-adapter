package org.purplebean.kmip.codec.ttlv.model.v3x0.enumeration;

import org.junit.jupiter.api.DisplayName;
import org.purplebean.kmip.model.v3x0.enumeration.ObjectClass;
import org.purplebean.kmip.test.suite.AbstractTtlvSerializationTestSuite;

@DisplayName("ObjectClass TTLV Serialization")
class ObjectClassTtlvTest extends AbstractTtlvSerializationTestSuite<ObjectClass> {
  @Override
  public Class<ObjectClass> type() {
    return ObjectClass.class;
  }

  @Override
  public ObjectClass createDefault() {
    return ObjectClass.Standard.USER.inst();
  }

  @Override
  public ObjectClass createVariant() {
    return ObjectClass.Standard.SYSTEM.inst();
  }
}
