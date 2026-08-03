package org.purpleBean.kmip.benchmark.subjects.model.v2x1.type;

import lombok.Getter;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purpleBean.kmip.model.v2x1.type.QuantumSafe;

public class QuantumSafeBenchmarkSubject extends KmipBenchmarkSubject<QuantumSafe> {

  @Getter
  private final KmipSpec spec = KmipSpec.V2_1;

  public QuantumSafeBenchmarkSubject() throws Exception {
    QuantumSafe subject = QuantumSafe.of(true);
    initialize(subject, QuantumSafe.class);
  }

  @Override
  public String name() {
    return "QuantumSafe";
  }
}