package org.purplebean.kmip.benchmark.subjects.model.v2x1.type;

import lombok.Getter;
import org.purplebean.kmip.api.KmipSpec;
import org.purplebean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purplebean.kmip.model.v2x1.type.AsynchronousCapability;

/**
 * Benchmark subject for {@link AsynchronousCapability}.
 */
public class AsynchronousCapabilityBenchmarkSubject
    extends KmipBenchmarkSubject<AsynchronousCapability> {

  @Getter
  private final KmipSpec spec = KmipSpec.UnknownVersion;

  /**
   * Constructs a new {@link AsynchronousCapabilityBenchmarkSubject}.
   */
  public AsynchronousCapabilityBenchmarkSubject() throws Exception {
    AsynchronousCapability subject = AsynchronousCapability.of(true);
    initialize(subject, AsynchronousCapability.class);
  }

  @Override
  public String name() {
    return "AsynchronousCapability";
  }
}