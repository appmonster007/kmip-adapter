package org.purpleBean.kmip.benchmark.subjects.model.v2_1.structure;

import lombok.Getter;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purpleBean.kmip.model.core.enumeration.RngAlgorithm;
import org.purpleBean.kmip.model.v2_1.structure.RngParameters;

public class RngParametersBenchmarkSubject extends KmipBenchmarkSubject<RngParameters> {

  @Getter
  private final KmipSpec spec = KmipSpec.UnknownVersion;

  public RngParametersBenchmarkSubject() throws Exception {
    RngParameters subject = RngParameters.of(RngAlgorithm.Standard.UNSPECIFIED.inst());
    initialize(subject, RngParameters.class);
  }

  @Override
  public String name() {
    return "RngParameters";
  }
}