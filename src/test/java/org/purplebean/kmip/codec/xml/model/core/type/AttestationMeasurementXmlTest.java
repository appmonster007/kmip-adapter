package org.purplebean.kmip.codec.xml.model.core.type;

import java.nio.ByteBuffer;
import org.junit.jupiter.api.DisplayName;
import org.purplebean.kmip.api.KmipSpec;
import org.purplebean.kmip.model.core.type.AttestationMeasurement;
import org.purplebean.kmip.test.suite.AbstractXmlSerializationTestSuite;

@DisplayName("AttestationMeasurement Xml Serialization Tests")
class AttestationMeasurementXmlTest
    extends AbstractXmlSerializationTestSuite<AttestationMeasurement> {

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