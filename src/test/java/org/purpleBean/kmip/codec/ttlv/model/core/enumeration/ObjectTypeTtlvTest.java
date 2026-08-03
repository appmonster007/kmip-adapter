package org.purpleBean.kmip.codec.ttlv.model.core.enumeration;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.model.core.enumeration.ObjectType;
import org.purpleBean.kmip.test.suite.AbstractTtlvSerializationTestSuite;

@DisplayName("ObjectType TTLV Serialization")
class ObjectTypeTtlvTest extends AbstractTtlvSerializationTestSuite<ObjectType> {
  @Override
  public Class<ObjectType> type() {
    return ObjectType.class;
  }

  @Override
  public ObjectType createDefault() {
    return ObjectType.Standard.CERTIFICATE.inst();
  }

  @Override
  public ObjectType createVariant() {
    return ObjectType.Standard.SYMMETRIC_KEY.inst();
  }
}
