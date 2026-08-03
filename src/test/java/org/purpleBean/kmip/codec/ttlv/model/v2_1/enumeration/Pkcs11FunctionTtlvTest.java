package org.purpleBean.kmip.codec.ttlv.model.v2_1.enumeration;

import java.util.Set;
import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.model.v2_1.enumeration.Pkcs11Function;
import org.purpleBean.kmip.test.suite.AbstractTtlvSerializationTestSuite;

@DisplayName("Pkcs11Function Ttlv Serialization Tests")
class Pkcs11FunctionTtlvTest extends AbstractTtlvSerializationTestSuite<Pkcs11Function> {

  @Override
  public Class<Pkcs11Function> type() {
    return Pkcs11Function.class;
  }

  @Override
  public Pkcs11Function createDefault() {
    return Pkcs11Function
        .register(0x80000031, "X-Ttlv-Default", Set.of(KmipSpec.UnknownVersion))
        .inst();
  }

  @Override
  public Pkcs11Function createVariant() {
    return Pkcs11Function
        .register(0x80000032, "X-Ttlv-Variant", Set.of(KmipSpec.UnknownVersion))
        .inst();
  }
}
