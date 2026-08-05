package org.purplebean.kmip.benchmark.subjects.model.v2x1.type;

import lombok.Getter;
import org.purplebean.kmip.api.KmipSpec;
import org.purplebean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purplebean.kmip.model.v2x1.type.NeverExtractable;

/**
 * Benchmark subject for {@link NeverExtractable}.
 */
public class NeverExtractableBenchmarkSubject extends KmipBenchmarkSubject<NeverExtractable> {

  @Getter
  private final KmipSpec spec = KmipSpec.V2_1;

  /**
   * Constructs a new {@link NeverExtractableBenchmarkSubject}.
   */
  public NeverExtractableBenchmarkSubject() throws Exception {
    NeverExtractable subject = NeverExtractable.of(true);
    initialize(subject, NeverExtractable.class);
  }

  @Override
  public String name() {
    return "NeverExtractable";
  }
}