package org.purpleBean.kmip.benchmark.subjects.model.core.type;

import java.math.BigInteger;
import lombok.Getter;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purpleBean.kmip.model.core.type.PrivateExponent;

public class PrivateExponentBenchmarkSubject extends KmipBenchmarkSubject<PrivateExponent> {

  @Getter
  private final KmipSpec spec = KmipSpec.V1_2;

  public PrivateExponentBenchmarkSubject() throws Exception {
    PrivateExponent privateExponent = PrivateExponent
        .builder()
        .value(BigInteger.valueOf(12345))
        .build();
    initialize(privateExponent, PrivateExponent.class);
  }

  @Override
  public String name() {
    return "PrivateExponent";
  }

}