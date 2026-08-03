package org.purplebean.kmip.benchmark.subjects.model.core.type;

import lombok.Getter;
import org.purplebean.kmip.api.KmipSpec;
import org.purplebean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purplebean.kmip.model.core.type.BatchCount;

public class BatchCountBenchmarkSubject extends KmipBenchmarkSubject<BatchCount> {

  @Getter
  private final KmipSpec spec = KmipSpec.V1_2;

  public BatchCountBenchmarkSubject() throws Exception {
    BatchCount batchCount = BatchCount
        .builder()
        .value(5)
        .build();
    initialize(batchCount, BatchCount.class);
  }

  @Override
  public String name() {
    return "BatchCount";
  }

}