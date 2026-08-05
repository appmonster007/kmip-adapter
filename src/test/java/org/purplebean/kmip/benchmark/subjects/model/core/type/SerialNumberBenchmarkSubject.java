package org.purplebean.kmip.benchmark.subjects.model.core.type;

import lombok.Getter;
import org.purplebean.kmip.api.KmipSpec;
import org.purplebean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purplebean.kmip.model.core.type.SerialNumber;

/**
 * Benchmark subject for {@link SerialNumber}.
 */
public class SerialNumberBenchmarkSubject extends KmipBenchmarkSubject<SerialNumber> {

  @Getter
  private final KmipSpec spec = KmipSpec.V1_2;

  /**
   * Constructs a new {@link SerialNumberBenchmarkSubject}.
   */
  public SerialNumberBenchmarkSubject() throws Exception {
    SerialNumber serialNumber = SerialNumber
        .builder()
        .value("12345")
        .build();
    initialize(serialNumber, SerialNumber.class);
  }

  @Override
  public String name() {
    return "SerialNumber";
  }

}