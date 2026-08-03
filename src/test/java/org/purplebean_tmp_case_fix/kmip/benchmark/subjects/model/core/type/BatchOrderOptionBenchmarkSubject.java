package org.purplebean.kmip.benchmark.subjects.model.core.type;

import lombok.Getter;
import org.purplebean.kmip.api.KmipSpec;
import org.purplebean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purplebean.kmip.model.core.type.BatchOrderOption;

public class BatchOrderOptionBenchmarkSubject extends KmipBenchmarkSubject<BatchOrderOption> {

  @Getter
  private final KmipSpec spec = KmipSpec.V1_2;

  public BatchOrderOptionBenchmarkSubject() throws Exception {
    BatchOrderOption batchOrderOption = BatchOrderOption
        .builder()
        .value(true)
        .build();
    initialize(batchOrderOption, BatchOrderOption.class);
  }

  @Override
  public String name() {
    return "BatchOrderOption";
  }

}