package org.purplebean.kmip.benchmark.subjects.model.v3x0.structure;

import lombok.Getter;
import org.purplebean.kmip.api.KmipSpec;
import org.purplebean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purplebean.kmip.model.v3x0.enumeration.DeactivationReasonCode;
import org.purplebean.kmip.model.v3x0.structure.DeactivationReason;

public class DeactivationReasonBenchmarkSubject extends KmipBenchmarkSubject<DeactivationReason> {

  @Getter
  private final KmipSpec spec = KmipSpec.V3_0;

  public DeactivationReasonBenchmarkSubject() throws Exception {
    DeactivationReason subject = DeactivationReason
        .builder()
        .deactivationReasonCode(
            DeactivationReasonCode.of(DeactivationReasonCode.Standard.UNSPECIFIED))
        .build();
    initialize(subject, DeactivationReason.class);
  }

  @Override
  public String name() {
    return "DeactivationReason";
  }
}