package org.purplebean.kmip.benchmark.subjects.model.v3x0.type;

import java.nio.ByteBuffer;
import lombok.Getter;
import org.purplebean.kmip.api.KmipSpec;
import org.purplebean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purplebean.kmip.model.v3x0.type.PasswordSalt;

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