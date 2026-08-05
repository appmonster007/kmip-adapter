package org.purplebean.kmip.benchmark.subjects.model.core.type;

import java.math.BigInteger;
import lombok.Getter;
import org.purplebean.kmip.api.KmipSpec;
import org.purplebean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purplebean.kmip.model.core.type.PrimeExponentQ;

/**
 * Benchmark subject for {@link PrimeExponentQ}.
 */
public class PrimeExponentQBenchmarkSubject extends KmipBenchmarkSubject<PrimeExponentQ> {

  @Getter
  private final KmipSpec spec = KmipSpec.V1_2;

  /**
   * Constructs a new {@link PrimeExponentQBenchmarkSubject}.
   */
  public PrimeExponentQBenchmarkSubject() throws Exception {
    PrimeExponentQ primeExponentQ = PrimeExponentQ
        .builder()
        .value(BigInteger.valueOf(65537))
        .build();
    initialize(primeExponentQ, PrimeExponentQ.class);
  }

  @Override
  public String name() {
    return "PrimeExponentQ";
  }

}