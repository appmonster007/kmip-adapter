package org.purplebean.kmip.benchmark.subjects.model.core.type;

import java.math.BigInteger;
import lombok.Getter;
import org.purplebean.kmip.api.KmipSpec;
import org.purplebean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purplebean.kmip.model.core.type.CRTCoefficient;

public class CRTCoefficientBenchmarkSubject extends KmipBenchmarkSubject<CRTCoefficient> {

  @Getter
  private final KmipSpec spec = KmipSpec.V1_2;

  public CRTCoefficientBenchmarkSubject() throws Exception {
    CRTCoefficient cRTCoefficient = CRTCoefficient
        .builder()
        .value(BigInteger.valueOf(12345))
        .build();
    initialize(cRTCoefficient, CRTCoefficient.class);
  }

  @Override
  public String name() {
    return "CRTCoefficient";
  }

}