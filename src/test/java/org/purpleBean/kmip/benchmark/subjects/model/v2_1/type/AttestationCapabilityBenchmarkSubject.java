package org.purpleBean.kmip.benchmark.subjects.model.v2_1.type;

import lombok.Getter;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purpleBean.kmip.model.v2_1.type.AttestationCapability;

public class AttestationCapabilityBenchmarkSubject
    extends KmipBenchmarkSubject<AttestationCapability> {

  @Getter
  private final KmipSpec spec = KmipSpec.UnknownVersion;

  public AttestationCapabilityBenchmarkSubject() throws Exception {
    AttestationCapability subject = AttestationCapability.of(true);
    initialize(subject, AttestationCapability.class);
  }

  @Override
  public String name() {
    return "AttestationCapability";
  }
}