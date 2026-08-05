package org.purplebean.kmip.benchmark.subjects.model.core.enumeration;

import org.purplebean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purplebean.kmip.model.core.enumeration.RevocationReasonCode;

/**
 * Benchmark subject for {@link RevocationReasonCode}.
 */
public class RevocationReasonCodeBenchmarkSubject
    extends KmipBenchmarkSubject<RevocationReasonCode> {

  /**
   * Constructs a new {@link RevocationReasonCodeBenchmarkSubject}.
   */
  public RevocationReasonCodeBenchmarkSubject() throws Exception {
    RevocationReasonCode revocationReasonCode = RevocationReasonCode.Standard.KEY_COMPROMISE.inst();
    initialize(revocationReasonCode, RevocationReasonCode.class);
  }

  @Override
  public String name() {
    return "RevocationReasonCode";
  }

}
