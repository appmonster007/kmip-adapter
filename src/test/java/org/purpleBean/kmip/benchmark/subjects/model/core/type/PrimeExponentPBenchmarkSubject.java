package org.purpleBean.kmip.benchmark.subjects.model.core.type;

import java.math.BigInteger;
import lombok.Getter;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purpleBean.kmip.model.core.type.PrimeExponentP;

public class PrimeExponentPBenchmarkSubject extends KmipBenchmarkSubject<PrimeExponentP> {

  @Getter
  private final KmipSpec spec = KmipSpec.V1_2;

  public PrimeExponentPBenchmarkSubject() throws Exception {
    PrimeExponentP primeExponentP = PrimeExponentP
        .builder()
        .value(BigInteger.valueOf(65537))
        .build();
    initialize(primeExponentP, PrimeExponentP.class);
  }

  @Override
  public String name() {
    return "PrimeExponentP";
  }

}