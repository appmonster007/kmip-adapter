package org.purpleBean.kmip.benchmark.subjects.model.v3_0.type;

import java.nio.ByteBuffer;
import lombok.Getter;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purpleBean.kmip.model.v3_0.type.PasswordSalt;

public class PasswordSaltBenchmarkSubject extends KmipBenchmarkSubject<PasswordSalt> {

  @Getter
  private final KmipSpec spec = KmipSpec.V3_0; // TODO: Adjust if needed

  public PasswordSaltBenchmarkSubject() throws Exception {
    PasswordSalt subject = PasswordSalt.of(
        ByteBuffer.wrap(new byte[] {0x01, 0x02, 0x03}));  // TODO: Create a default instance
    initialize(subject, PasswordSalt.class);
  }

  @Override
  public String name() {
    return "PasswordSalt";
  }
}