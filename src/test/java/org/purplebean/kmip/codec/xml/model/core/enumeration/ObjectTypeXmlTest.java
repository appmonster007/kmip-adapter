package org.purplebean.kmip.codec.xml.model.core.enumeration;

import org.junit.jupiter.api.DisplayName;
import org.purplebean.kmip.model.core.enumeration.ObjectType;
import org.purplebean.kmip.test.suite.AbstractXmlSerializationTestSuite;

@DisplayName("ObjectType XML Serialization")
class ObjectTypeXmlTest extends AbstractXmlSerializationTestSuite<ObjectType> {
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
