package org.purplebean.kmip.benchmark.subjects.model.v3x0.enumeration;

import org.purplebean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purplebean.kmip.model.v3x0.enumeration.DeactivationReasonCode;

/**
 * Benchmark subject for {@link DeactivationReasonCode}.
 */
public class DeactivationReasonCodeBenchmarkSubject
    extends KmipBenchmarkSubject<DeactivationReasonCode> {

  /**
   * Constructs a new {@link DeactivationReasonCodeBenchmarkSubject}.
   */
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
