package org.purplebean.kmip.benchmark.subjects.model.core.type;

import java.math.BigInteger;
import lombok.Getter;
import org.purplebean.kmip.api.KmipSpec;
import org.purplebean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purplebean.kmip.model.core.type.Q;

public class QBenchmarkSubject extends KmipBenchmarkSubject<Q> {

  @Getter
  private final KmipSpec spec = KmipSpec.V1_2;

  public QBenchmarkSubject() throws Exception {
    Q q = Q
        .builder()
        .value(BigInteger.ONE)
        .build();
    initialize(q, Q.class);
  }

  @Override
  public String name() {
    return "Q";
  }

}