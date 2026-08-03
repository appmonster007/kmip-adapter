package org.purpleBean.kmip.model.core.structure;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.api.EncodingType;
import org.purpleBean.kmip.api.KmipDataType;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.model.core.type.DeviceSerialNumber;
import org.purpleBean.kmip.test.suite.AbstractKmipStructureTestSuite;

@DisplayName("DeviceCredential Domain Tests")
class DeviceCredentialTest extends AbstractKmipStructureTestSuite<DeviceCredential> {

  @Override
  protected void setupDefaultSpec() {
    defaultSpec = KmipSpec.UnknownVersion;
  }

  @Override
  protected Class<DeviceCredential> type() {
    return DeviceCredential.class;
  }

  @Override
  protected DeviceCredential createDefault() {
    return DeviceCredential
        .builder()
        .deviceSerialNumber(DeviceSerialNumber.of("test-serial-number"))
        .build();
  }

  @Override
  protected EncodingType expectedEncodingType() {
    return EncodingType.STRUCTURE;
  }

  @Override
  protected int expectedMinComponentCount() {
    return 1;
  }

  @Override
  protected void validateComponents(List<KmipDataType> values) {
    assertThat(values).hasSize(1);
    assertThat(values.getFirst()).isInstanceOf(DeviceSerialNumber.class);
  }
}