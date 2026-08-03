package org.purpleBean.kmip.benchmark.subjects.model.core.type;

import java.math.BigInteger;
import lombok.Getter;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purpleBean.kmip.model.core.type.Q;

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