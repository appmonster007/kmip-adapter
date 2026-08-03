package org.purplebean.kmip.benchmark.subjects.model.core.type;

import java.math.BigInteger;
import lombok.Getter;
import org.purplebean.kmip.api.KmipSpec;
import org.purplebean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purplebean.kmip.model.core.type.P;

public class PBenchmarkSubject extends KmipBenchmarkSubject<P> {

  @Getter
  private final KmipSpec spec = KmipSpec.V1_2;

  public PBenchmarkSubject() throws Exception {
    P p = P
        .builder()
        .value(BigInteger.ONE)
        .build();
    initialize(p, P.class);
  }

  @Override
  public String name() {
    return "P";
  }

}