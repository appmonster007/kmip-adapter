package org.purplebean.kmip.benchmark.subjects.model.v2x1.type;

import lombok.Getter;
import org.purplebean.kmip.api.KmipSpec;
import org.purplebean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purplebean.kmip.model.v2x1.type.BatchContinueCapability;

/**
 * Benchmark subject for {@link BatchContinueCapability}.
 */
public class BatchContinueCapabilityBenchmarkSubject
    extends KmipBenchmarkSubject<BatchContinueCapability> {

  @Getter
  private final KmipSpec spec = KmipSpec.UnknownVersion;

  /**
   * Constructs a new {@link BatchContinueCapabilityBenchmarkSubject}.
   */
  public BatchContinueCapabilityBenchmarkSubject() throws Exception {
    BatchContinueCapability subject = BatchContinueCapability.of(true);
    initialize(subject, BatchContinueCapability.class);
  }

  @Override
  public String name() {
    return "BatchContinueCapability";
  }
}