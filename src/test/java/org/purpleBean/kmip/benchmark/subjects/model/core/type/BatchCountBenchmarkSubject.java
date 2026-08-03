package org.purpleBean.kmip.benchmark.subjects.model.core.type;

import lombok.Getter;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purpleBean.kmip.model.core.type.BatchCount;

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