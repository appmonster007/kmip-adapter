package org.purplebean.kmip.benchmark.subjects.model.v2x1.structure;

import lombok.Getter;
import org.purplebean.kmip.api.KmipSpec;
import org.purplebean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purplebean.kmip.model.v2x1.structure.CapabilityInformation;

/**
 * Benchmark subject for {@link CapabilityInformation}.
 */
public class CapabilityInformationBenchmarkSubject
    extends KmipBenchmarkSubject<CapabilityInformation> {

  @Getter
  private final KmipSpec spec = KmipSpec.UnknownVersion;

  /**
   * Constructs a new {@link CapabilityInformationBenchmarkSubject}.
   */
  public CapabilityInformationBenchmarkSubject() throws Exception {
    CapabilityInformation subject = CapabilityInformation
        .builder()
        .build();
    initialize(subject, CapabilityInformation.class);
  }

  @Override
  public String name() {
    return "CapabilityInformation";
  }
}