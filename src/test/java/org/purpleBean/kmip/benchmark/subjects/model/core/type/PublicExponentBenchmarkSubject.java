package org.purplebean.kmip.benchmark.subjects.model.core.type;

import java.math.BigInteger;
import lombok.Getter;
import org.purplebean.kmip.api.KmipSpec;
import org.purplebean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purplebean.kmip.model.core.type.PublicExponent;

public class PublicExponentBenchmarkSubject extends KmipBenchmarkSubject<PublicExponent> {

  @Getter
  private final KmipSpec spec = KmipSpec.V1_2;

  public PublicExponentBenchmarkSubject() throws Exception {
    PublicExponent publicExponent = PublicExponent
        .builder()
        .value(BigInteger.valueOf(65537))
        .build();
    initialize(publicExponent, PublicExponent.class);
  }

  @Override
  public String name() {
    return "PublicExponent";
  }

}