package org.purpleBean.kmip.benchmark.subjects.model.v3x0.type;

import lombok.Getter;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purpleBean.kmip.model.v3x0.type.OtpCounter;

public class OtpCounterBenchmarkSubject extends KmipBenchmarkSubject<OtpCounter> {

  @Getter
  private final KmipSpec spec = KmipSpec.V3_0; // TODO: Adjust if needed

  public OtpCounterBenchmarkSubject() throws Exception {
    OtpCounter subject = OtpCounter.of(1);  // TODO: Create a default instance
    initialize(subject, OtpCounter.class);
  }

  @Override
  public String name() {
    return "OtpCounter";
  }
}