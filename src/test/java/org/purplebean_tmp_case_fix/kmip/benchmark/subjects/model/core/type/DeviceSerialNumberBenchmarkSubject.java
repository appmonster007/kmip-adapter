package org.purplebean.kmip.benchmark.subjects.model.core.type;

import lombok.Getter;
import org.purplebean.kmip.api.KmipSpec;
import org.purplebean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purplebean.kmip.model.core.type.DeviceSerialNumber;

public class DeviceSerialNumberBenchmarkSubject extends KmipBenchmarkSubject<DeviceSerialNumber> {

  @Getter
  private final KmipSpec spec = KmipSpec.V1_2;

  public DeviceSerialNumberBenchmarkSubject() throws Exception {
    DeviceSerialNumber deviceSerialNumber = DeviceSerialNumber
        .builder()
        .value("12345")
        .build();
    initialize(deviceSerialNumber, DeviceSerialNumber.class);
  }

  @Override
  public String name() {
    return "DeviceSerialNumber";
  }

}