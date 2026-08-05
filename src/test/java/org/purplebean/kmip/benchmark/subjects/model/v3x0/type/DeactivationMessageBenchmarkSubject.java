package org.purplebean.kmip.benchmark.subjects.model.v3x0.type;

import lombok.Getter;
import org.purplebean.kmip.api.KmipSpec;
import org.purplebean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purplebean.kmip.model.v3x0.type.DeactivationMessage;

/**
 * Benchmark subject for {@link DeactivationMessage}.
 */
public class DeactivationMessageBenchmarkSubject extends KmipBenchmarkSubject<DeactivationMessage> {

  @Getter
  private final KmipSpec spec = KmipSpec.V3_0; // TODO: Adjust if needed

  /**
   * Constructs a new {@link DeactivationMessageBenchmarkSubject}.
   */
  public DeactivationMessageBenchmarkSubject() throws Exception {
    DeactivationMessage subject =
        DeactivationMessage.of("default-string");  // TODO: Create a default instance
    initialize(subject, DeactivationMessage.class);
  }

  @Override
  public String name() {
    return "DeactivationMessage";
  }
}