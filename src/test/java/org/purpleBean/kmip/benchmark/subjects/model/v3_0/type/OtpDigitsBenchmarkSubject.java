package org.purpleBean.kmip.benchmark.subjects.model.v3_0.type;

import lombok.Getter;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purpleBean.kmip.model.v3_0.type.OtpDigits;

public class OtpDigitsBenchmarkSubject extends KmipBenchmarkSubject<OtpDigits> {

  @Getter
  private final KmipSpec spec = KmipSpec.V3_0; // TODO: Adjust if needed

  public OtpDigitsBenchmarkSubject() throws Exception {
    OtpDigits subject = OtpDigits.of(1);  // TODO: Create a default instance
    initialize(subject, OtpDigits.class);
  }

  @Override
  public String name() {
    return "OtpDigits";
  }
}