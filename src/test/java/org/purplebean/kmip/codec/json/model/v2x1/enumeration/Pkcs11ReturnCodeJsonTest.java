package org.purplebean.kmip.codec.json.model.v2x1.enumeration;

import java.util.Set;
import org.junit.jupiter.api.DisplayName;
import org.purplebean.kmip.api.KmipSpec;
import org.purplebean.kmip.model.v2x1.enumeration.Pkcs11ReturnCode;
import org.purplebean.kmip.test.suite.AbstractJsonSerializationTestSuite;

@DisplayName("Pkcs11ReturnCode Json Serialization Tests")
class Pkcs11ReturnCodeJsonTest extends AbstractJsonSerializationTestSuite<Pkcs11ReturnCode> {

  @Override
  public Class<Pkcs11ReturnCode> type() {
    return Pkcs11ReturnCode.class;
  }

  @Override
  public Pkcs11ReturnCode createDefault() {
    return Pkcs11ReturnCode
        .register(0x80000021, "X-Json-Default", Set.of(KmipSpec.UnknownVersion))
        .inst();
  }

  @Override
  public Pkcs11ReturnCode createVariant() {
    return Pkcs11ReturnCode
        .register(0x80000022, "X-Json-Variant", Set.of(KmipSpec.UnknownVersion))
        .inst();
  }
}