package org.purpleBean.kmip.benchmark.subjects.model.v3x0.type;

import java.nio.ByteBuffer;
import lombok.Getter;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purpleBean.kmip.model.v3x0.type.HashedPasswordUsername;

public class HashedPasswordUsernameBenchmarkSubject
    extends KmipBenchmarkSubject<HashedPasswordUsername> {

  @Getter
  private final KmipSpec spec = KmipSpec.V3_0; // TODO: Adjust if needed

  public HashedPasswordUsernameBenchmarkSubject() throws Exception {
    HashedPasswordUsername subject = HashedPasswordUsername.of(
        ByteBuffer.wrap(new byte[] {0x01, 0x02, 0x03}));  // TODO: Create a default instance
    initialize(subject, HashedPasswordUsername.class);
  }

  @Override
  public String name() {
    return "HashedPasswordUsername";
  }
}