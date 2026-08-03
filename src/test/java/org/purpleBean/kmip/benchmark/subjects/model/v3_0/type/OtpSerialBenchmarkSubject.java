package org.purpleBean.kmip.benchmark.subjects.model.v3_0.type;

import lombok.Getter;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purpleBean.kmip.model.v3_0.type.OtpSerial;

public class OtpSerialBenchmarkSubject extends KmipBenchmarkSubject<OtpSerial> {

  @Getter
  private final KmipSpec spec = KmipSpec.V3_0; // TODO: Adjust if needed

  public OtpSerialBenchmarkSubject() throws Exception {
    OtpSerial subject = OtpSerial.of("default-string");  // TODO: Create a default instance
    initialize(subject, OtpSerial.class);
  }

  @Override
  public String name() {
    return "OtpSerial";
  }
}