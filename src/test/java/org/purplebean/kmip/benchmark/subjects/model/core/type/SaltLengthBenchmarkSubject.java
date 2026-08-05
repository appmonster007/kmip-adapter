package org.purplebean.kmip.benchmark.subjects.model.core.type;

import org.purplebean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purplebean.kmip.model.core.type.SaltLength;

/**
 * Benchmark subject for {@link SaltLength}.
 */
public class SaltLengthBenchmarkSubject extends KmipBenchmarkSubject<SaltLength> {

  /**
   * Constructs a new {@link SaltLengthBenchmarkSubject}.
   */
  public SaltLengthBenchmarkSubject() throws Exception {
    SaltLength saltLength = SaltLength.of(123);
    initialize(saltLength, SaltLength.class);
  }

  @Override
  public String name() {
    return "SaltLength";
  }

}
