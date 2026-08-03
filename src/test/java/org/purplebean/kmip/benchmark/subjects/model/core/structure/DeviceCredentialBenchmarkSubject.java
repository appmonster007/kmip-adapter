package org.purplebean.kmip.benchmark.subjects.model.core.structure;

import lombok.Getter;
import org.purplebean.kmip.api.KmipSpec;
import org.purplebean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purplebean.kmip.model.core.structure.DeviceCredential;
import org.purplebean.kmip.model.core.type.DeviceSerialNumber;

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