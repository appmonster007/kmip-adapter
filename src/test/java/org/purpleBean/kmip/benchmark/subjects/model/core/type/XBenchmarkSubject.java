package org.purpleBean.kmip.benchmark.subjects.model.core.type;

import java.math.BigInteger;
import lombok.Getter;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purpleBean.kmip.model.core.type.X;

public class XBenchmarkSubject extends KmipBenchmarkSubject<X> {

  @Getter
  private final KmipSpec spec = KmipSpec.V1_2;

  public XBenchmarkSubject() throws Exception {
    X x = X
        .builder()
        .value(BigInteger.ONE)
        .build();
    initialize(x, X.class);
  }

  @Override
  public String name() {
    return "X";
  }

}