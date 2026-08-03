package org.purplebean.kmip.benchmark.subjects.model.core.type;

import java.math.BigInteger;
import lombok.Getter;
import org.purplebean.kmip.api.KmipSpec;
import org.purplebean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purplebean.kmip.model.core.type.PrivateExponent;

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