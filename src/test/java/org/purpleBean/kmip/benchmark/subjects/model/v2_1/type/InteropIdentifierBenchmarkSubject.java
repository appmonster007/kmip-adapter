package org.purpleBean.kmip.benchmark.subjects.model.v2_1.type;

import lombok.Getter;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purpleBean.kmip.model.v2_1.type.InteropIdentifier;

public class InteropIdentifierBenchmarkSubject extends KmipBenchmarkSubject<InteropIdentifier> {

  @Getter
  private final KmipSpec spec = KmipSpec.V2_1; // TODO: Adjust if needed

  public InteropIdentifierBenchmarkSubject() throws Exception {
    InteropIdentifier subject =
        InteropIdentifier.of("default-string");  // TODO: Create a default instance
    initialize(subject, InteropIdentifier.class);
  }

  @Override
  public String name() {
    return "InteropIdentifier";
  }
}