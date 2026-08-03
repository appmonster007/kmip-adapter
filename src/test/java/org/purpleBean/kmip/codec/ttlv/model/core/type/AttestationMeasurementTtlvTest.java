package org.purpleBean.kmip.codec.ttlv.model.core.type;

import java.nio.ByteBuffer;
import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.model.core.type.AttestationMeasurement;
import org.purpleBean.kmip.test.suite.AbstractTtlvSerializationTestSuite;

@DisplayName("AttestationMeasurement Ttlv Serialization Tests")
class AttestationMeasurementTtlvTest
    extends AbstractTtlvSerializationTestSuite<AttestationMeasurement> {

  @Override
  protected void setupDefaultSpec() {
    defaultSpec = KmipSpec.UnknownVersion;
  }

  @Override
  public Class<AttestationMeasurement> type() {
    return AttestationMeasurement.class;
  }

  @Override
  public AttestationMeasurement createDefault() {
    return AttestationMeasurement.of(ByteBuffer.wrap(new byte[] {0x01, 0x02, 0x03}));
  }

  @Override
  public AttestationMeasurement createVariant() {
    return AttestationMeasurement.of(ByteBuffer.wrap(new byte[] {0x04, 0x05, 0x06}));
  }
}