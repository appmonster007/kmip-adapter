package org.purpleBean.kmip.benchmark.subjects.model.v2x1.structure;

import lombok.Getter;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purpleBean.kmip.model.v2x1.structure.CapabilityInformation;

public class CapabilityInformationBenchmarkSubject
    extends KmipBenchmarkSubject<CapabilityInformation> {

  @Getter
  private final KmipSpec spec = KmipSpec.UnknownVersion;

  public CapabilityInformationBenchmarkSubject() throws Exception {
    CapabilityInformation subject = CapabilityInformation
        .builder()
        .build();
    initialize(subject, CapabilityInformation.class);
  }

  @Override
  public String name() {
    return "CapabilityInformation";
  }
}