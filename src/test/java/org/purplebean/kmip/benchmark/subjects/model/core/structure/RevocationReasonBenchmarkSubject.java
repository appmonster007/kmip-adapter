package org.purplebean.kmip.benchmark.subjects.model.core.structure;

import org.purplebean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purplebean.kmip.model.core.enumeration.RevocationReasonCode;
import org.purplebean.kmip.model.core.structure.RevocationReason;
import org.purplebean.kmip.model.core.type.RevocationMessage;

/**
 * Benchmark subject for {@link RevocationReason}.
 */
public class RevocationReasonBenchmarkSubject extends KmipBenchmarkSubject<RevocationReason> {

  /**
   * Constructs a new {@link RevocationReasonBenchmarkSubject}.
   */
  public RevocationReasonBenchmarkSubject() throws Exception {
    RevocationReason revocationReason = RevocationReason
        .builder()
        .revocationReasonCode(RevocationReasonCode.Standard.KEY_COMPROMISE.inst())
        .revocationMessage(RevocationMessage.of("test-message"))
        .build();
    initialize(revocationReason, RevocationReason.class);
  }

  @Override
  public String name() {
    return "RevocationReason";
  }

}
