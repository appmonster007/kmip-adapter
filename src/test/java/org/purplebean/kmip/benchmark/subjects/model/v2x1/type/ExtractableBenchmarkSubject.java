package org.purplebean.kmip.benchmark.subjects.model.v2x1.type;

import lombok.Getter;
import org.purplebean.kmip.api.KmipSpec;
import org.purplebean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purplebean.kmip.model.v2x1.type.Extractable;

/**
 * Benchmark subject for {@link Extractable}.
 */
public class ExtractableBenchmarkSubject extends KmipBenchmarkSubject<Extractable> {

  @Getter
  private final KmipSpec spec = KmipSpec.V2_1;

  /**
   * Constructs a new {@link ExtractableBenchmarkSubject}.
   */
  public ExtractableBenchmarkSubject() throws Exception {
    Extractable subject = Extractable.of(true);
    initialize(subject, Extractable.class);
  }

  @Override
  public String name() {
    return "Extractable";
  }
}