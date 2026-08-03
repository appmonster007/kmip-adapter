package org.purplebean.kmip.codec.xml.model.v2x1.enumeration;

import java.util.Set;
import org.junit.jupiter.api.DisplayName;
import org.purplebean.kmip.api.KmipSpec;
import org.purplebean.kmip.model.v2x1.enumeration.Pkcs11Function;
import org.purplebean.kmip.test.suite.AbstractXmlSerializationTestSuite;

@DisplayName("Pkcs11Function Xml Serialization Tests")
class Pkcs11FunctionXmlTest extends AbstractXmlSerializationTestSuite<Pkcs11Function> {

  @Override
  public Class<Pkcs11Function> type() {
    return Pkcs11Function.class;
  }

  @Override
  public Pkcs11Function createDefault() {
    return Pkcs11Function
        .register(0x80000041, "X-Xml-Default", Set.of(KmipSpec.UnknownVersion))
        .inst();
  }

  @Override
  public Pkcs11Function createVariant() {
    return Pkcs11Function
        .register(0x80000042, "X-Xml-Variant", Set.of(KmipSpec.UnknownVersion))
        .inst();
  }
}
