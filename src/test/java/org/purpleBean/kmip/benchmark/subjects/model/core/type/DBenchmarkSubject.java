package org.purpleBean.kmip.benchmark.subjects.model.core.type;

import java.math.BigInteger;
import lombok.Getter;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purpleBean.kmip.model.core.type.D;

public class DBenchmarkSubject extends KmipBenchmarkSubject<D> {

  @Getter
  private final KmipSpec spec = KmipSpec.V1_2;

  public DBenchmarkSubject() throws Exception {
    D d = D
        .builder()
        .value(BigInteger.ONE)
        .build();
    initialize(d, D.class);
  }

  @Override
  public String name() {
    return "D";
  }

}