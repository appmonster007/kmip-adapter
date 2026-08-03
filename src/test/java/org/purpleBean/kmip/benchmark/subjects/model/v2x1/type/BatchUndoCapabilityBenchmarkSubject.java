package org.purpleBean.kmip.benchmark.subjects.model.v2x1.type;

import lombok.Getter;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purpleBean.kmip.model.v2x1.type.BatchUndoCapability;

public class BatchUndoCapabilityBenchmarkSubject extends KmipBenchmarkSubject<BatchUndoCapability> {

  @Getter
  private final KmipSpec spec = KmipSpec.UnknownVersion;

  public BatchUndoCapabilityBenchmarkSubject() throws Exception {
    BatchUndoCapability subject = BatchUndoCapability.of(true);
    initialize(subject, BatchUndoCapability.class);
  }

  @Override
  public String name() {
    return "BatchUndoCapability";
  }
}