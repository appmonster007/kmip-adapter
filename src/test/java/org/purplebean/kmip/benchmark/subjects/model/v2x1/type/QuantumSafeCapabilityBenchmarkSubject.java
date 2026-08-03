package org.purplebean.kmip.benchmark.subjects.model.v2x1.type;

import lombok.Getter;
import org.purplebean.kmip.api.KmipSpec;
import org.purplebean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purplebean.kmip.model.v2x1.type.QuantumSafeCapability;

public class QuantumSafeCapabilityBenchmarkSubject
    extends KmipBenchmarkSubject<QuantumSafeCapability> {

  @Getter
  private final KmipSpec spec = KmipSpec.UnknownVersion;

  public QuantumSafeCapabilityBenchmarkSubject() throws Exception {
    QuantumSafeCapability subject = QuantumSafeCapability.of(true);
    initialize(subject, QuantumSafeCapability.class);
  }

  @Override
  public String name() {
    return "QuantumSafeCapability";
  }
}