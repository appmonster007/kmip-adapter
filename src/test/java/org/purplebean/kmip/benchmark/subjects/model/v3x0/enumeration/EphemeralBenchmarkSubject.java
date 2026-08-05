package org.purplebean.kmip.benchmark.subjects.model.v3x0.enumeration;

import org.purplebean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purplebean.kmip.model.v3x0.enumeration.Ephemeral;

/**
 * Benchmark subject for {@link Ephemeral}.
 */
public class EphemeralBenchmarkSubject extends KmipBenchmarkSubject<Ephemeral> {

  /**
   * Constructs a new {@link EphemeralBenchmarkSubject}.
   */
  public EphemeralBenchmarkSubject() throws Exception {
    Ephemeral ephemeral = Ephemeral.Standard.DATA.inst();
    initialize(ephemeral, Ephemeral.class);
  }

  @Override
  public String name() {
    return "Ephemeral";
  }

}
