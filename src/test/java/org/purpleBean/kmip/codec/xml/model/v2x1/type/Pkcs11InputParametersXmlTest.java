package org.purpleBean.kmip.codec.xml.model.v2x1.type;

import java.nio.ByteBuffer;
import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.model.v2x1.type.Pkcs11InputParameters;
import org.purpleBean.kmip.test.suite.AbstractXmlSerializationTestSuite;

@DisplayName("Pkcs11InputParameters Xml Serialization Tests")
class Pkcs11InputParametersXmlTest
    extends AbstractXmlSerializationTestSuite<Pkcs11InputParameters> {

  @Override
  public Class<Pkcs11InputParameters> type() {
    return Pkcs11InputParameters.class;
  }

  @Override
  public Pkcs11InputParameters createDefault() {
    return Pkcs11InputParameters.of(ByteBuffer.wrap(new byte[] {0x01, 0x02, 0x03}));
  }

  @Override
  public Pkcs11InputParameters createVariant() {
    return Pkcs11InputParameters.of(ByteBuffer.wrap(new byte[] {0x04, 0x05, 0x06}));
  }
}