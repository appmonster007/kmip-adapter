package org.purplebean.kmip.codec.ttlv.model.core.type;

import java.nio.ByteBuffer;
import org.junit.jupiter.api.DisplayName;
import org.purplebean.kmip.model.core.type.SignatureData;
import org.purplebean.kmip.test.suite.AbstractTtlvSerializationTestSuite;

@DisplayName("SignatureData TTLV Serialization Tests")
class SignatureDataTtlvTest extends AbstractTtlvSerializationTestSuite<SignatureData> {

  @Override
  public Class<SignatureData> type() {
    return SignatureData.class;
  }

  @Override
  public SignatureData createDefault() {
    return SignatureData.of(ByteBuffer.wrap("test signature data".getBytes()));
  }

  @Override
  public SignatureData createVariant() {
    return SignatureData.of(ByteBuffer.wrap("variant signature data".getBytes()));
  }
}
