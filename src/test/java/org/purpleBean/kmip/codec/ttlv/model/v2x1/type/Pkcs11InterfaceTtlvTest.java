package org.purplebean.kmip.codec.ttlv.model.v2x1.type;

import org.junit.jupiter.api.DisplayName;
import org.purplebean.kmip.model.v2x1.type.Pkcs11Interface;
import org.purplebean.kmip.test.suite.AbstractTtlvSerializationTestSuite;

@DisplayName("Pkcs11Interface Ttlv Serialization Tests")
class Pkcs11InterfaceTtlvTest extends AbstractTtlvSerializationTestSuite<Pkcs11Interface> {

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