package org.purplebean.kmip.benchmark.subjects.model.v3x0.type;

import lombok.Getter;
import org.purplebean.kmip.api.KmipSpec;
import org.purplebean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purplebean.kmip.model.v3x0.type.OtpInterval;

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