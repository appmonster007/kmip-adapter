package org.purplebean.kmip.codec.ttlv.model.core.type;

import org.junit.jupiter.api.DisplayName;
import org.purplebean.kmip.model.core.type.InitializationVector;
import org.purplebean.kmip.test.suite.AbstractTtlvSerializationTestSuite;

@DisplayName("InitializationVector TTLV Serialization Tests")
class InitializationVectorTtlvTest
    extends AbstractTtlvSerializationTestSuite<InitializationVector> {

  @Override
  public Class<InitializationVector> type() {
    return InitializationVector.class;
  }

  @Override
  public InitializationVector createDefault() {
    return InitializationVector.of(new byte[] {0x01, 0x02, 0x03});
  }

  @Override
  public InitializationVector createVariant() {
    return InitializationVector.of(new byte[] {0x04, 0x05, 0x06});
  }
}