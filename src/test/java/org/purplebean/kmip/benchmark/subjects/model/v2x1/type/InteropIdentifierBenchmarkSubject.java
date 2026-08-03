package org.purplebean.kmip.benchmark.subjects.model.v2x1.type;

import lombok.Getter;
import org.purplebean.kmip.api.KmipSpec;
import org.purplebean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purplebean.kmip.model.v2x1.type.InteropIdentifier;

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