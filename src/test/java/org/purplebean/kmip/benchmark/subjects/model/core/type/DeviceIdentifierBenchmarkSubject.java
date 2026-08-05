package org.purplebean.kmip.benchmark.subjects.model.core.type;

import lombok.Getter;
import org.purplebean.kmip.api.KmipSpec;
import org.purplebean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purplebean.kmip.model.core.type.DeviceIdentifier;

/**
 * Benchmark subject for {@link DeviceIdentifier}.
 */
public class DeviceIdentifierBenchmarkSubject extends KmipBenchmarkSubject<DeviceIdentifier> {

  @Getter
  private final KmipSpec spec = KmipSpec.V1_2;

  /**
   * Constructs a new {@link DeviceIdentifierBenchmarkSubject}.
   */
  public DeviceIdentifierBenchmarkSubject() throws Exception {
    DeviceIdentifier deviceIdentifier = DeviceIdentifier
        .builder()
        .value("test-device-id")
        .build();
    initialize(deviceIdentifier, DeviceIdentifier.class);
  }

  @Override
  public String name() {
    return "DeviceIdentifier";
  }

}