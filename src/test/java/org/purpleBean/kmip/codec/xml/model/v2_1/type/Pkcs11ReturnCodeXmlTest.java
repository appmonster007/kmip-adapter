package org.purpleBean.kmip.codec.xml.model.v2_1.type;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.model.v2_1.type.Pkcs11ReturnCode;
import org.purpleBean.kmip.test.suite.AbstractXmlSerializationTestSuite;

@DisplayName("Pkcs11ReturnCode Xml Serialization Tests")
class Pkcs11ReturnCodeXmlTest extends AbstractXmlSerializationTestSuite<Pkcs11ReturnCode> {

  @Override
  public Class<Pkcs11ReturnCode> type() {
    return Pkcs11ReturnCode.class;
  }

  @Override
  public Pkcs11ReturnCode createDefault() {
    return Pkcs11ReturnCode.of(1);
  }

  @Override
  public Pkcs11ReturnCode createVariant() {
    return Pkcs11ReturnCode.of(2);
  }
}