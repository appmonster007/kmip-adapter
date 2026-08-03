package org.purplebean.kmip.codec.xml.model.core.enumeration;

import org.junit.jupiter.api.DisplayName;
import org.purplebean.kmip.model.core.enumeration.KeyRoleType;
import org.purplebean.kmip.test.suite.AbstractXmlSerializationTestSuite;

@DisplayName("KeyRoleType XML Serialization")
class KeyRoleTypeXmlTest extends AbstractXmlSerializationTestSuite<KeyRoleType> {
  @Override
  public Class<KeyRoleType> type() {
    return KeyRoleType.class;
  }

  @Override
  public KeyRoleType createDefault() {
    return KeyRoleType.Standard.BDK.inst();
  }

  @Override
  public KeyRoleType createVariant() {
    return KeyRoleType.Standard.CVK.inst();
  }
}
