package org.purpleBean.kmip.benchmark.subjects.model.v3_0.type;

import lombok.Getter;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purpleBean.kmip.model.v3_0.type.OtpInterval;

public class OtpIntervalBenchmarkSubject extends KmipBenchmarkSubject<OtpInterval> {

  @Getter
  private final KmipSpec spec = KmipSpec.V3_0; // TODO: Adjust if needed

  public OtpIntervalBenchmarkSubject() throws Exception {
    OtpInterval subject = OtpInterval.of(1);  // TODO: Create a default instance
    initialize(subject, OtpInterval.class);
  }

  @Override
  public String name() {
    return "OtpInterval";
  }
}