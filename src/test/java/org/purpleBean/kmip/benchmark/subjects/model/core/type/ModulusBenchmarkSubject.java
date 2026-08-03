package org.purpleBean.kmip.benchmark.subjects.model.core.type;

import java.math.BigInteger;
import lombok.Getter;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purpleBean.kmip.model.core.type.Modulus;

public class ModulusBenchmarkSubject extends KmipBenchmarkSubject<Modulus> {

  @Getter
  private final KmipSpec spec = KmipSpec.V1_2;

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