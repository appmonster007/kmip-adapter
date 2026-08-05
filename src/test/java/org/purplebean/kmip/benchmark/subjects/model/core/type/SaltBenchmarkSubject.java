package org.purplebean.kmip.benchmark.subjects.model.core.type;

import lombok.Getter;
import org.purplebean.kmip.api.KmipSpec;
import org.purplebean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purplebean.kmip.model.core.type.Salt;

/**
 * Benchmark subject for {@link Salt}.
 */
public class SaltBenchmarkSubject extends KmipBenchmarkSubject<Salt> {

  @Getter
  private final KmipSpec spec = KmipSpec.V1_2;

  /**
   * Constructs a new {@link SaltBenchmarkSubject}.
   */
  public SaltBenchmarkSubject() throws Exception {
    Salt salt = Salt.of(new byte[] {0x01, 0x02, 0x03});
    initialize(salt, Salt.class);
  }

  @Override
  public String name() {
    return "Salt";
  }

}