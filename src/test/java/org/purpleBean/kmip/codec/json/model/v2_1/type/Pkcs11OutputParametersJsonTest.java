package org.purpleBean.kmip.codec.json.model.v2_1.type;

import java.nio.ByteBuffer;
import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.model.v2_1.type.Pkcs11OutputParameters;
import org.purpleBean.kmip.test.suite.AbstractJsonSerializationTestSuite;

@DisplayName("Pkcs11OutputParameters Json Serialization Tests")
class Pkcs11OutputParametersJsonTest
    extends AbstractJsonSerializationTestSuite<Pkcs11OutputParameters> {

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