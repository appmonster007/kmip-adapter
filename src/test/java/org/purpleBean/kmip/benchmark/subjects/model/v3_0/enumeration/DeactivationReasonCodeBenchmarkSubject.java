package org.purpleBean.kmip.benchmark.subjects.model.v3_0.enumeration;

import org.purpleBean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purpleBean.kmip.model.v3_0.enumeration.DeactivationReasonCode;

public class DeactivationReasonCodeBenchmarkSubject
    extends KmipBenchmarkSubject<DeactivationReasonCode> {

  public DeactivationReasonCodeBenchmarkSubject() throws Exception {
    DeactivationReasonCode deactivationReasonCode =
        DeactivationReasonCode.Standard.UNSPECIFIED.inst();
    initialize(deactivationReasonCode, DeactivationReasonCode.class);
  }

  @Override
  public String name() {
    return "DeactivationReasonCode";
  }

}
