package org.purplebean.kmip.benchmark.subjects.model.v2x1.structure;

import lombok.Getter;
import org.purplebean.kmip.api.KmipSpec;
import org.purplebean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purplebean.kmip.model.v2x1.structure.AsynchronousCorrelationValues;

/**
 * Benchmark subject for {@link AsynchronousCorrelationValues}.
 */
public class AsynchronousCorrelationValuesBenchmarkSubject
    extends KmipBenchmarkSubject<AsynchronousCorrelationValues> {

  @Getter
  private final KmipSpec spec = KmipSpec.UnknownVersion;

  /**
   * Constructs a new {@link AsynchronousCorrelationValuesBenchmarkSubject}.
   */
  public AsynchronousCorrelationValuesBenchmarkSubject() throws Exception {
    AsynchronousCorrelationValues subject = AsynchronousCorrelationValues.of(java.util.List.of());
    initialize(subject, AsynchronousCorrelationValues.class);
  }

  @Override
  public String name() {
    return "AsynchronousCorrelationValues";
  }
}