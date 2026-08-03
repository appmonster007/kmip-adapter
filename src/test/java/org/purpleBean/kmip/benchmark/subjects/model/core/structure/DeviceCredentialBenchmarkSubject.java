package org.purpleBean.kmip.benchmark.subjects.model.core.structure;

import lombok.Getter;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purpleBean.kmip.model.core.structure.DeviceCredential;
import org.purpleBean.kmip.model.core.type.DeviceSerialNumber;

public class DeviceCredentialBenchmarkSubject extends KmipBenchmarkSubject<DeviceCredential> {

  @Getter
  private final KmipSpec spec = KmipSpec.UnknownVersion;

  public DeviceCredentialBenchmarkSubject() throws Exception {
    DeviceCredential subject = DeviceCredential
        .builder()
        .deviceSerialNumber(DeviceSerialNumber.of("test-serial-number"))
        .build();
    initialize(subject, DeviceCredential.class);
  }

  @Override
  public String name() {
    return "DeviceCredential";
  }

}