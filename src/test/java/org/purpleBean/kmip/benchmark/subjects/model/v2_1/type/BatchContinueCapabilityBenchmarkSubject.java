package org.purpleBean.kmip.benchmark.subjects.model.v2_1.type;

import lombok.Getter;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purpleBean.kmip.model.v2_1.type.BatchContinueCapability;

public class BatchContinueCapabilityBenchmarkSubject
    extends KmipBenchmarkSubject<BatchContinueCapability> {

  @Getter
  private final KmipSpec spec = KmipSpec.UnknownVersion;

  public BatchContinueCapabilityBenchmarkSubject() throws Exception {
    BatchContinueCapability subject = BatchContinueCapability.of(true);
    initialize(subject, BatchContinueCapability.class);
  }

  @Override
  public String name() {
    return "BatchContinueCapability";
  }
}