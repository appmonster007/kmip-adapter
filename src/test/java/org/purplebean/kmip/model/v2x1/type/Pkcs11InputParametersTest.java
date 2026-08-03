package org.purplebean.kmip.model.v2x1.type;

import java.nio.ByteBuffer;
import org.junit.jupiter.api.DisplayName;
import org.purplebean.kmip.api.EncodingType;
import org.purplebean.kmip.api.KmipSpec;
import org.purplebean.kmip.test.suite.AbstractKmipDataTypeTestSuite;

@DisplayName("Pkcs11InputParameters Domain Tests")
class Pkcs11InputParametersTest extends AbstractKmipDataTypeTestSuite<Pkcs11InputParameters> {

  @Override
  protected void setupDefaultSpec() {
    defaultSpec = KmipSpec.V2_1;
  }

  @Override
  protected Class<Pkcs11InputParameters> type() {
    return Pkcs11InputParameters.class;
  }

  @Override
  protected Pkcs11InputParameters createDefault() {
    return Pkcs11InputParameters.of(ByteBuffer.wrap(new byte[] {0x01, 0x02, 0x03}));
  }

  @Override
  protected EncodingType expectedEncodingType() {
    return EncodingType.BYTE_STRING;
  }
}