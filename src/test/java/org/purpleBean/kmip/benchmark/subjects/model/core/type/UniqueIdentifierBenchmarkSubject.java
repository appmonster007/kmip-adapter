package org.purplebean.kmip.benchmark.subjects.model.core.type;

import org.purplebean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purplebean.kmip.model.core.type.UniqueIdentifier;

public class UniqueIdentifierBenchmarkSubject extends KmipBenchmarkSubject<UniqueIdentifier> {

  public UniqueIdentifierBenchmarkSubject() throws Exception {
    UniqueIdentifier uniqueIdentifier = UniqueIdentifier
        .builder()
        .value("FIXED_STRING")
        .build();
    initialize(uniqueIdentifier, UniqueIdentifier.class);
  }

  @Override
  public String name() {
    return "UniqueIdentifier";
  }

}
