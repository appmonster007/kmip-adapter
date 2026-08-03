package org.purplebean.kmip.codec.json.model.core.structure;

import org.junit.jupiter.api.DisplayName;
import org.purplebean.kmip.api.KmipSpec;
import org.purplebean.kmip.model.core.structure.DeviceCredential;
import org.purplebean.kmip.model.core.type.DeviceSerialNumber;
import org.purplebean.kmip.test.suite.AbstractJsonSerializationTestSuite;

@DisplayName("DeviceCredential Json Serialization Tests")
class DeviceCredentialJsonTest extends AbstractJsonSerializationTestSuite<DeviceCredential> {

  @Override
  protected void setupDefaultSpec() {
    defaultSpec = KmipSpec.UnknownVersion;
  }

  @Override
  public Class<DeviceCredential> type() {
    return DeviceCredential.class;
  }

  @Override
  public DeviceCredential createDefault() {
    return DeviceCredential
        .builder()
        .deviceSerialNumber(DeviceSerialNumber.of("test-serial-number"))
        .build();
  }

  @Override
  public DeviceCredential createVariant() {
    return DeviceCredential
        .builder()
        .deviceSerialNumber(DeviceSerialNumber.of("test-serial-number-variant"))
        .build();
  }
}