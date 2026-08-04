package org.purplebean.kmip.codec.ttlv.model.v2x1.enumeration;

import java.util.Set;
import org.junit.jupiter.api.DisplayName;
import org.purplebean.kmip.api.KmipSpec;
import org.purplebean.kmip.model.v2x1.enumeration.Pkcs11ReturnCode;
import org.purplebean.kmip.test.suite.AbstractTtlvSerializationTestSuite;

@DisplayName("Pkcs11ReturnCode Ttlv Serialization Tests")
class Pkcs11ReturnCodeTtlvTest extends AbstractTtlvSerializationTestSuite<Pkcs11ReturnCode> {

  @Override
  public Class<Pkcs11ReturnCode> type() {
    return Pkcs11ReturnCode.class;
  }

  @Override
  public Pkcs11ReturnCode createDefault() {
    return Pkcs11ReturnCode
        .register(0x80000031, "X-Ttlv-Default", Set.of(KmipSpec.UnknownVersion))
        .inst();
  }

  @Override
  public Pkcs11ReturnCode createVariant() {
    return Pkcs11ReturnCode
        .register(0x80000032, "X-Ttlv-Variant", Set.of(KmipSpec.UnknownVersion))
        .inst();
  }
}
