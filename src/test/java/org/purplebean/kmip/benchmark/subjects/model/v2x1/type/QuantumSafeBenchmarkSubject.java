package org.purplebean.kmip.benchmark.subjects.model.v2x1.type;

import lombok.Getter;
import org.purplebean.kmip.api.KmipSpec;
import org.purplebean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purplebean.kmip.model.v2x1.type.QuantumSafe;

/**
 * Benchmark subject for {@link QuantumSafe}.
 */
public class QuantumSafeBenchmarkSubject extends KmipBenchmarkSubject<QuantumSafe> {

  @Getter
  private final KmipSpec spec = KmipSpec.V2_1;

  /**
   * Constructs a new {@link QuantumSafeBenchmarkSubject}.
   */
  public QuantumSafeBenchmarkSubject() throws Exception {
    QuantumSafe subject = QuantumSafe.of(true);
    initialize(subject, QuantumSafe.class);
  }

  @Override
  public String name() {
    return "QuantumSafe";
  }
}