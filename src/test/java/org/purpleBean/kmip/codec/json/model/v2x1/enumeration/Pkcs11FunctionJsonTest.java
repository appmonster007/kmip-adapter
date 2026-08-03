package org.purpleBean.kmip.codec.json.model.v2x1.enumeration;

import java.util.Set;
import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.model.v2x1.enumeration.Pkcs11Function;
import org.purpleBean.kmip.test.suite.AbstractJsonSerializationTestSuite;

@DisplayName("Pkcs11Function Json Serialization Tests")
class Pkcs11FunctionJsonTest extends AbstractJsonSerializationTestSuite<Pkcs11Function> {

  @Override
  public Class<Pkcs11Function> type() {
    return Pkcs11Function.class;
  }

  @Override
  public Pkcs11Function createDefault() {
    return Pkcs11Function
        .register(0x80000021, "X-Json-Default", Set.of(KmipSpec.UnknownVersion))
        .inst();
  }

  @Override
  public Pkcs11Function createVariant() {
    return Pkcs11Function
        .register(0x80000022, "X-Json-Variant", Set.of(KmipSpec.UnknownVersion))
        .inst();
  }
}