package org.purplebean.kmip.benchmark.subjects.model.core.type;

import lombok.Getter;
import org.purplebean.kmip.api.KmipSpec;
import org.purplebean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purplebean.kmip.model.core.type.MaximumResponseSize;

public class MaximumResponseSizeBenchmarkSubject extends KmipBenchmarkSubject<MaximumResponseSize> {

  @Getter
  private final KmipSpec spec = KmipSpec.V1_2;

  public MaximumResponseSizeBenchmarkSubject() throws Exception {
    MaximumResponseSize maximumResponseSize = MaximumResponseSize
        .builder()
        .value(1024)
        .build();
    initialize(maximumResponseSize, MaximumResponseSize.class);
  }

  @Override
  public String name() {
    return "MaximumResponseSize";
  }

}