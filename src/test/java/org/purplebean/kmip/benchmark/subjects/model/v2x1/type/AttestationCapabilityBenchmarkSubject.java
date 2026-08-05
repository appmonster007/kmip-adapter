package org.purplebean.kmip.benchmark.subjects.model.v2x1.type;

import lombok.Getter;
import org.purplebean.kmip.api.KmipSpec;
import org.purplebean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purplebean.kmip.model.v2x1.type.AttestationCapability;

/**
 * Benchmark subject for {@link AttestationCapability}.
 */
public class AttestationCapabilityBenchmarkSubject
    extends KmipBenchmarkSubject<AttestationCapability> {

  @Getter
  private final KmipSpec spec = KmipSpec.UnknownVersion;

  /**
   * Constructs a new {@link AttestationCapabilityBenchmarkSubject}.
   */
  public AttestationCapabilityBenchmarkSubject() throws Exception {
    AttestationCapability subject = AttestationCapability.of(true);
    initialize(subject, AttestationCapability.class);
  }

  @Override
  public String name() {
    return "AttestationCapability";
  }
}