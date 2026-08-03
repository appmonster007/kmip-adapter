package org.purpleBean.kmip.codec.xml.model.core.enumeration;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.model.core.enumeration.KeyRoleType;
import org.purpleBean.kmip.test.suite.AbstractXmlSerializationTestSuite;

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
