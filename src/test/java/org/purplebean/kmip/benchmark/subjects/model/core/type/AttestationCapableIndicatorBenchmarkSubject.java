package org.purplebean.kmip.benchmark.subjects.model.core.type;

import lombok.Getter;
import org.purplebean.kmip.api.KmipSpec;
import org.purplebean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purplebean.kmip.model.core.type.AttestationCapableIndicator;

/**
 * Benchmark subject for {@link AttestationCapableIndicator}.
 */
public class AttestationCapableIndicatorBenchmarkSubject
    extends KmipBenchmarkSubject<AttestationCapableIndicator> {

  @Getter
  private final KmipSpec spec = KmipSpec.UnknownVersion;

  /**
   * Constructs a new {@link AttestationCapableIndicatorBenchmarkSubject}.
   */
  public AttestationCapableIndicatorBenchmarkSubject() throws Exception {
    AttestationCapableIndicator subject = AttestationCapableIndicator.of(true);
    initialize(subject, AttestationCapableIndicator.class);
  }

  @Override
  public String name() {
    return "AttestationCapableIndicator";
  }

}