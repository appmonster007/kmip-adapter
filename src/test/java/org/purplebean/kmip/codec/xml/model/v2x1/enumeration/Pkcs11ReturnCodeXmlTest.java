package org.purplebean.kmip.codec.xml.model.v2x1.enumeration;

import java.util.Set;
import org.junit.jupiter.api.DisplayName;
import org.purplebean.kmip.api.KmipSpec;
import org.purplebean.kmip.model.v2x1.enumeration.Pkcs11ReturnCode;
import org.purplebean.kmip.test.suite.AbstractXmlSerializationTestSuite;

@DisplayName("Pkcs11ReturnCode Xml Serialization Tests")
class Pkcs11ReturnCodeXmlTest extends AbstractXmlSerializationTestSuite<Pkcs11ReturnCode> {

  @Override
  public Class<Pkcs11ReturnCode> type() {
    return Pkcs11ReturnCode.class;
  }

  @Override
  public Pkcs11ReturnCode createDefault() {
    return Pkcs11ReturnCode
        .register(0x80000041, "X-Xml-Default", Set.of(KmipSpec.UnknownVersion))
        .inst();
  }

  @Override
  public Pkcs11ReturnCode createVariant() {
    return Pkcs11ReturnCode
        .register(0x80000042, "X-Xml-Variant", Set.of(KmipSpec.UnknownVersion))
        .inst();
  }
}
