package org.purplebean.kmip.model.core.type;

import org.junit.jupiter.api.DisplayName;
import org.purplebean.kmip.api.EncodingType;
import org.purplebean.kmip.test.suite.AbstractKmipDataTypeTestSuite;

@DisplayName("DeviceIdentifier Domain Tests")
class DeviceIdentifierTest extends AbstractKmipDataTypeTestSuite<DeviceIdentifier> {

  @Override
  protected Class<DeviceIdentifier> type() {
    return DeviceIdentifier.class;
  }

  @Override
  protected DeviceIdentifier createDefault() {
    return DeviceIdentifier
        .builder()
        .value("test-device-id")
        .build();
  }

  @Override
  protected EncodingType expectedEncodingType() {
    return EncodingType.TEXT_STRING;
  }
}