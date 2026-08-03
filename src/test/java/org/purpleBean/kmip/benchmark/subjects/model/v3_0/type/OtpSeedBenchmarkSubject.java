package org.purpleBean.kmip.benchmark.subjects.model.v3_0.type;

import java.nio.ByteBuffer;
import lombok.Getter;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purpleBean.kmip.model.v3_0.type.OtpSeed;

public class OtpSeedBenchmarkSubject extends KmipBenchmarkSubject<OtpSeed> {

  @Getter
  private final KmipSpec spec = KmipSpec.V3_0; // TODO: Adjust if needed

  public OtpSeedBenchmarkSubject() throws Exception {
    OtpSeed subject = OtpSeed.of(
        ByteBuffer.wrap(new byte[] {0x01, 0x02, 0x03}));  // TODO: Create a default instance
    initialize(subject, OtpSeed.class);
  }

  @Override
  public String name() {
    return "OtpSeed";
  }
}