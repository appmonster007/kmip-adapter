package org.purplebean.kmip.benchmark.subjects.model.core.type;

import java.math.BigInteger;
import lombok.Getter;
import org.purplebean.kmip.api.KmipSpec;
import org.purplebean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purplebean.kmip.model.core.type.PrimeFieldSize;

public class PrimeFieldSizeBenchmarkSubject extends KmipBenchmarkSubject<PrimeFieldSize> {

  @Getter
  private final KmipSpec spec = KmipSpec.V1_2;

  public PrimeFieldSizeBenchmarkSubject() throws Exception {
    PrimeFieldSize primeFieldSize = PrimeFieldSize
        .builder()
        .value(BigInteger.valueOf(2048))
        .build();
    initialize(primeFieldSize, PrimeFieldSize.class);
  }

  @Override
  public String name() {
    return "PrimeFieldSize";
  }

}