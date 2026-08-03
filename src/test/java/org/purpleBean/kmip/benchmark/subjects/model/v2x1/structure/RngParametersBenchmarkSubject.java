package org.purplebean.kmip.benchmark.subjects.model.v2x1.structure;

import lombok.Getter;
import org.purplebean.kmip.api.KmipSpec;
import org.purplebean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purplebean.kmip.model.core.enumeration.RngAlgorithm;
import org.purplebean.kmip.model.v2x1.structure.RngParameters;

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