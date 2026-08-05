package org.purplebean.kmip.benchmark.subjects.model.core.enumeration;

import org.purplebean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purplebean.kmip.model.core.enumeration.AttestationType;

/**
 * Benchmark subject for {@link AttestationType}.
 */
public class AttestationTypeBenchmarkSubject extends KmipBenchmarkSubject<AttestationType> {

  /**
   * Constructs a new {@link AttestationTypeBenchmarkSubject}.
   */
  public AttestationTypeBenchmarkSubject() throws Exception {
    AttestationType attestationType = AttestationType.Standard.TPM_QUOTE.inst();
    initialize(attestationType, AttestationType.class);
  }

  @Override
  public String name() {
    return "AttestationType";
  }

}
