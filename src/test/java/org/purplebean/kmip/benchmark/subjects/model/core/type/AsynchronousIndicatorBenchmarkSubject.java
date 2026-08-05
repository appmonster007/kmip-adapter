package org.purplebean.kmip.benchmark.subjects.model.core.type;

import lombok.Getter;
import org.purplebean.kmip.api.KmipSpec;
import org.purplebean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purplebean.kmip.model.core.type.AsynchronousIndicator;

/**
 * Benchmark subject for {@link AsynchronousIndicator}.
 */
public class AsynchronousIndicatorBenchmarkSubject
    extends KmipBenchmarkSubject<AsynchronousIndicator> {

  @Getter
  private final KmipSpec spec = KmipSpec.V1_2;

  /**
   * Constructs a new {@link AsynchronousIndicatorBenchmarkSubject}.
   */
  public AsynchronousIndicatorBenchmarkSubject() throws Exception {
    AsynchronousIndicator asynchronousIndicator = AsynchronousIndicator
        .builder()
        .value(true)
        .build();
    initialize(asynchronousIndicator, AsynchronousIndicator.class);
  }

  @Override
  public String name() {
    return "AsynchronousIndicator";
  }

}