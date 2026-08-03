package org.purplebean.kmip.model.v2x1.type;

import java.nio.ByteBuffer;
import org.junit.jupiter.api.DisplayName;
import org.purplebean.kmip.api.EncodingType;
import org.purplebean.kmip.api.KmipSpec;
import org.purplebean.kmip.test.suite.AbstractKmipDataTypeTestSuite;


@DisplayName("Pkcs11OutputParameters Domain Tests")
class Pkcs11OutputParametersTest extends AbstractKmipDataTypeTestSuite<Pkcs11OutputParameters> {

  @Override
  protected void setupDefaultSpec() {
    defaultSpec = KmipSpec.V2_1;
  }

  @Override
  protected Class<Pkcs11OutputParameters> type() {
    return Pkcs11OutputParameters.class;
  }

  @Override
  protected Pkcs11OutputParameters createDefault() {
    return Pkcs11OutputParameters.of(ByteBuffer.wrap(new byte[] {0x01, 0x02, 0x03}));
  }

  @Override
  protected EncodingType expectedEncodingType() {
    return EncodingType.BYTE_STRING;
  }
}