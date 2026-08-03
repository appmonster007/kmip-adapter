package org.purplebean.kmip.benchmark.subjects.model.core.type;

import lombok.Getter;
import org.purplebean.kmip.api.KmipSpec;
import org.purplebean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purplebean.kmip.model.core.type.LinkedObjectIdentifier;

public class LinkedObjectIdentifierBenchmarkSubject
    extends KmipBenchmarkSubject<LinkedObjectIdentifier> {

  @Getter
  private final KmipSpec spec = KmipSpec.V1_2;

  public LinkedObjectIdentifierBenchmarkSubject() throws Exception {
    LinkedObjectIdentifier linkedObjectIdentifier = LinkedObjectIdentifier
        .builder()
        .value("test-linked-id")
        .build();
    initialize(linkedObjectIdentifier, LinkedObjectIdentifier.class);
  }

  @Override
  public String name() {
    return "LinkedObjectIdentifier";
  }

}