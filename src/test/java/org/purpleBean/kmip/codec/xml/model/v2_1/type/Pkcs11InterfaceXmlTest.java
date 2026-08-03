package org.purpleBean.kmip.codec.xml.model.v2_1.type;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.model.v2_1.type.Pkcs11Interface;
import org.purpleBean.kmip.test.suite.AbstractXmlSerializationTestSuite;

@DisplayName("Pkcs11Interface Xml Serialization Tests")
class Pkcs11InterfaceXmlTest extends AbstractXmlSerializationTestSuite<Pkcs11Interface> {

  @Override
  public Class<Pkcs11Interface> type() {
    return Pkcs11Interface.class;
  }

  @Override
  public Pkcs11Interface createDefault() {
    return Pkcs11Interface.of("default-string");
  }

  @Override
  public Pkcs11Interface createVariant() {
    return Pkcs11Interface.of("variant-string");
  }
}