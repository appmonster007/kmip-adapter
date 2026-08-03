package org.purplebean.kmip.codec.ttlv.model.core.type;

import org.junit.jupiter.api.DisplayName;
import org.purplebean.kmip.model.core.type.DeviceSerialNumber;
import org.purplebean.kmip.test.suite.AbstractTtlvSerializationTestSuite;

@DisplayName("DeviceSerialNumber TTLV Serialization Tests")
class DeviceSerialNumberTtlvTest extends AbstractTtlvSerializationTestSuite<DeviceSerialNumber> {

  @Override
  public Class<DeviceSerialNumber> type() {
    return DeviceSerialNumber.class;
  }

  @Override
  public DeviceSerialNumber createDefault() {
    return DeviceSerialNumber
        .builder()
        .value("12345")
        .build();
  }

  @Override
  public DeviceSerialNumber createVariant() {
    return DeviceSerialNumber
        .builder()
        .value("67890")
        .build();
  }
}