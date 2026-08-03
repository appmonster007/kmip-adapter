package org.purpleBean.kmip.codec.json.model.v2x1.type;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.model.v2x1.type.Pkcs11ReturnCode;
import org.purpleBean.kmip.test.suite.AbstractJsonSerializationTestSuite;

@DisplayName("Pkcs11ReturnCode Json Serialization Tests")
class Pkcs11ReturnCodeJsonTest extends AbstractJsonSerializationTestSuite<Pkcs11ReturnCode> {

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