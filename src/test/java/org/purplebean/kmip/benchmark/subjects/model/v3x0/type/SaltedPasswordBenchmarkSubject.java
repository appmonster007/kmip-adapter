package org.purplebean.kmip.benchmark.subjects.model.v3x0.type;

import java.nio.ByteBuffer;
import lombok.Getter;
import org.purplebean.kmip.api.KmipSpec;
import org.purplebean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purplebean.kmip.model.v3x0.type.SaltedPassword;

/**
 * Benchmark subject for {@link SaltedPassword}.
 */
public class SaltedPasswordBenchmarkSubject extends KmipBenchmarkSubject<SaltedPassword> {

  @Getter
  private final KmipSpec spec = KmipSpec.V3_0; // TODO: Adjust if needed

  /**
   * Constructs a new {@link SaltedPasswordBenchmarkSubject}.
   */
  public SaltedPasswordBenchmarkSubject() throws Exception {
    SaltedPassword subject = SaltedPassword.of(
        ByteBuffer.wrap(new byte[] {0x01, 0x02, 0x03}));  // TODO: Create a default instance
    initialize(subject, SaltedPassword.class);
  }

  @Override
  public String name() {
    return "SaltedPassword";
  }
}