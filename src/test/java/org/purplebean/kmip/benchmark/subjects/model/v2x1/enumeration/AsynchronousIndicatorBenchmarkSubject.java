package org.purplebean.kmip.benchmark.subjects.model.v2x1.enumeration;

import lombok.Getter;
import org.purplebean.kmip.api.KmipSpec;
import org.purplebean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purplebean.kmip.model.v2x1.enumeration.AsynchronousIndicator;

/**
 * Benchmark subject for {@link AsynchronousIndicator}.
 */
public class AsynchronousIndicatorBenchmarkSubject
    extends KmipBenchmarkSubject<AsynchronousIndicator> {

  @Getter
  private final KmipSpec spec = KmipSpec.UnknownVersion;

  /**
   * Constructs a new {@link AsynchronousIndicatorBenchmarkSubject}.
   */
  public AsynchronousIndicatorBenchmarkSubject() throws Exception {
    AsynchronousIndicator subject = AsynchronousIndicator.Standard.values()[0].inst();
    initialize(subject, AsynchronousIndicator.class);
  }

  @Override
  public String name() {
    return "AsynchronousIndicator";
  }
}