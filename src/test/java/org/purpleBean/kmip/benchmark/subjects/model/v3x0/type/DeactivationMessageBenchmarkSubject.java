package org.purpleBean.kmip.benchmark.subjects.model.v3x0.type;

import lombok.Getter;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purpleBean.kmip.model.v3x0.type.DeactivationMessage;

public class DeactivationMessageBenchmarkSubject extends KmipBenchmarkSubject<DeactivationMessage> {

  @Getter
  private final KmipSpec spec = KmipSpec.V3_0; // TODO: Adjust if needed

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