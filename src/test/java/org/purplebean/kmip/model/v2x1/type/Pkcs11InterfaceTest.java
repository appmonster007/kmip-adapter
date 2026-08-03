package org.purplebean.kmip.model.v2x1.type;

import org.junit.jupiter.api.DisplayName;
import org.purplebean.kmip.api.EncodingType;
import org.purplebean.kmip.api.KmipSpec;
import org.purplebean.kmip.test.suite.AbstractKmipDataTypeTestSuite;

@DisplayName("Pkcs11Interface Domain Tests")
class Pkcs11InterfaceTest extends AbstractKmipDataTypeTestSuite<Pkcs11Interface> {

  @Override
  protected void setupDefaultSpec() {
    defaultSpec = KmipSpec.UnknownVersion;
  }

  @Override
  protected Class<Pkcs11Interface> type() {
    return Pkcs11Interface.class;
  }

  @Override
  protected Pkcs11Interface createDefault() {
    return Pkcs11Interface.of("default-string");
  }

  @Override
  protected EncodingType expectedEncodingType() {
    return EncodingType.TEXT_STRING;
  }
}