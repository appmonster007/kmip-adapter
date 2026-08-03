package org.purplebean.kmip.codec.ttlv.model.v2x1.type;

import org.junit.jupiter.api.DisplayName;
import org.purplebean.kmip.model.v2x1.type.Pkcs11ReturnCode;
import org.purplebean.kmip.test.suite.AbstractTtlvSerializationTestSuite;

@DisplayName("Pkcs11ReturnCode Ttlv Serialization Tests")
class Pkcs11ReturnCodeTtlvTest extends AbstractTtlvSerializationTestSuite<Pkcs11ReturnCode> {

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