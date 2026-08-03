package org.purplebean.kmip.benchmark.subjects.model.core.type;

import lombok.Getter;
import org.purplebean.kmip.api.KmipSpec;
import org.purplebean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purplebean.kmip.model.core.type.SplitKeyParts;

public class SplitKeyPartsBenchmarkSubject extends KmipBenchmarkSubject<SplitKeyParts> {

  @Getter
  private final KmipSpec spec = KmipSpec.V1_2;

  public SplitKeyPartsBenchmarkSubject() throws Exception {
    SplitKeyParts splitKeyParts = SplitKeyParts
        .builder()
        .value(2)
        .build();
    initialize(splitKeyParts, SplitKeyParts.class);
  }

  @Override
  public String name() {
    return "SplitKeyParts";
  }

}