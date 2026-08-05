package org.purplebean.kmip.benchmark.subjects.model.core.type;

import java.math.BigInteger;
import lombok.Getter;
import org.purplebean.kmip.api.KmipSpec;
import org.purplebean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purplebean.kmip.model.core.type.CRTCoefficient;

/**
 * Benchmark subject for {@link CRTCoefficient}.
 */
public class CRTCoefficientBenchmarkSubject extends KmipBenchmarkSubject<CRTCoefficient> {

  @Getter
  private final KmipSpec spec = KmipSpec.V1_2;

  /**
   * Constructs a new {@link CRTCoefficientBenchmarkSubject}.
   */
  public CRTCoefficientBenchmarkSubject() throws Exception {
    CRTCoefficient crtCoefficient = CRTCoefficient
        .builder()
        .value(BigInteger.valueOf(12345))
        .build();
    initialize(crtCoefficient, CRTCoefficient.class);
  }

  @Override
  public String name() {
    return "CRTCoefficient";
  }

}