package org.purplebean.kmip.benchmark.subjects.model.v3x0.type;

import lombok.Getter;
import org.purplebean.kmip.api.KmipSpec;
import org.purplebean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purplebean.kmip.model.v3x0.type.OtpSerial;

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