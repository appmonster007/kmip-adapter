package org.purplebean.kmip.benchmark.subjects.model.v2x1.type;

import lombok.Getter;
import org.purplebean.kmip.api.KmipSpec;
import org.purplebean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purplebean.kmip.model.v2x1.type.Ephemeral;

/**
 * Benchmark subject for {@link Ephemeral}.
 */
public class EphemeralBenchmarkSubject extends KmipBenchmarkSubject<Ephemeral> {

  @Getter
  private final KmipSpec spec = KmipSpec.UnknownVersion;

  /**
   * Constructs a new {@link EphemeralBenchmarkSubject}.
   */
  public EphemeralBenchmarkSubject() throws Exception {
    Ephemeral subject = Ephemeral.of(true);
    initialize(subject, Ephemeral.class);
  }

  @Override
  public String name() {
    return "Ephemeral";
  }
}