package org.purpleBean.kmip.codec.xml.model.v2x1.type;

import java.nio.ByteBuffer;
import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.model.v2x1.type.Pkcs11OutputParameters;
import org.purpleBean.kmip.test.suite.AbstractXmlSerializationTestSuite;

@DisplayName("Pkcs11OutputParameters Xml Serialization Tests")
class Pkcs11OutputParametersXmlTest
    extends AbstractXmlSerializationTestSuite<Pkcs11OutputParameters> {

  @Override
  public Class<Pkcs11OutputParameters> type() {
    return Pkcs11OutputParameters.class;
  }

  @Override
  public Pkcs11OutputParameters createDefault() {
    return Pkcs11OutputParameters.of(ByteBuffer.wrap(new byte[] {0x01, 0x02, 0x03}));
  }

  @Override
  public Pkcs11OutputParameters createVariant() {
    return Pkcs11OutputParameters.of(ByteBuffer.wrap(new byte[] {0x04, 0x05, 0x06}));
  }
}