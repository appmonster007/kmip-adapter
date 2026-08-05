package org.purplebean.kmip.benchmark.subjects.model.v3x0.type;

import java.nio.ByteBuffer;
import lombok.Getter;
import org.purplebean.kmip.api.KmipSpec;
import org.purplebean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purplebean.kmip.model.v3x0.type.HashedPasswordUsername;

/**
 * Benchmark subject for {@link HashedPasswordUsername}.
 */
public class HashedPasswordUsernameBenchmarkSubject
    extends KmipBenchmarkSubject<HashedPasswordUsername> {

  @Getter
  private final KmipSpec spec = KmipSpec.V3_0; // TODO: Adjust if needed

  /**
   * Constructs a new {@link HashedPasswordUsernameBenchmarkSubject}.
   */
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