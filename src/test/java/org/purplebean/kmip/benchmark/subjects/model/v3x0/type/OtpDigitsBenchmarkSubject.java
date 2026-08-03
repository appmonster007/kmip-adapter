package org.purplebean.kmip.benchmark.subjects.model.v3x0.type;

import lombok.Getter;
import org.purplebean.kmip.api.KmipSpec;
import org.purplebean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purplebean.kmip.model.v3x0.type.OtpDigits;

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