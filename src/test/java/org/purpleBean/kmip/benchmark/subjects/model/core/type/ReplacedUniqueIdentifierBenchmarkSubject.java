package org.purplebean.kmip.benchmark.subjects.model.core.type;

import lombok.Getter;
import org.purplebean.kmip.api.KmipSpec;
import org.purplebean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purplebean.kmip.model.core.type.ReplacedUniqueIdentifier;

public class ReplacedUniqueIdentifierBenchmarkSubject
    extends KmipBenchmarkSubject<ReplacedUniqueIdentifier> {

  @Getter
  private final KmipSpec spec = KmipSpec.V1_2;

  public ReplacedUniqueIdentifierBenchmarkSubject() throws Exception {
    ReplacedUniqueIdentifier replacedUniqueIdentifier = ReplacedUniqueIdentifier
        .builder()
        .value("test-id")
        .build();
    initialize(replacedUniqueIdentifier, ReplacedUniqueIdentifier.class);
  }

  @Override
  public String name() {
    return "ReplacedUniqueIdentifier";
  }

}