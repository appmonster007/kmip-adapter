package org.purplebean.kmip.benchmark.subjects.model.v2x1.type;

import lombok.Getter;
import org.purplebean.kmip.api.KmipSpec;
import org.purplebean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purplebean.kmip.model.v2x1.type.ReplaceExisting;

/**
 * Benchmark subject for {@link ReplaceExisting}.
 */
public class ReplaceExistingBenchmarkSubject extends KmipBenchmarkSubject<ReplaceExisting> {

  @Getter
  private final KmipSpec spec = KmipSpec.V2_1;

  /**
   * Constructs a new {@link ReplaceExistingBenchmarkSubject}.
   */
  public ReplaceExistingBenchmarkSubject() throws Exception {
    ReplaceExisting subject = ReplaceExisting.of(true);
    initialize(subject, ReplaceExisting.class);
  }

  @Override
  public String name() {
    return "ReplaceExisting";
  }
}