package org.purplebean.kmip.benchmark.subjects.model.core.type;

import java.math.BigInteger;
import lombok.Getter;
import org.purplebean.kmip.api.KmipSpec;
import org.purplebean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purplebean.kmip.model.core.type.Modulus;

/**
 * Benchmark subject for {@link Modulus}.
 */
public class ModulusBenchmarkSubject extends KmipBenchmarkSubject<Modulus> {

  @Getter
  private final KmipSpec spec = KmipSpec.V1_2;

  /**
   * Constructs a new {@link ModulusBenchmarkSubject}.
   */
  public ModulusBenchmarkSubject() throws Exception {
    Modulus modulus = Modulus
        .builder()
        .value(BigInteger.TEN)
        .build();
    initialize(modulus, Modulus.class);
  }

  @Override
  public String name() {
    return "Modulus";
  }

}