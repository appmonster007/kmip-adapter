package org.purplebean.kmip.benchmark.subjects.model.core.type;

import lombok.Getter;
import org.purplebean.kmip.api.KmipSpec;
import org.purplebean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purplebean.kmip.model.core.type.SplitKeyThreshold;

public class SplitKeyThresholdBenchmarkSubject extends KmipBenchmarkSubject<SplitKeyThreshold> {

  @Getter
  private final KmipSpec spec = KmipSpec.V1_2;

  public SplitKeyThresholdBenchmarkSubject() throws Exception {
    SplitKeyThreshold splitKeyThreshold = SplitKeyThreshold
        .builder()
        .value(2)
        .build();
    initialize(splitKeyThreshold, SplitKeyThreshold.class);
  }

  @Override
  public String name() {
    return "SplitKeyThreshold";
  }

}