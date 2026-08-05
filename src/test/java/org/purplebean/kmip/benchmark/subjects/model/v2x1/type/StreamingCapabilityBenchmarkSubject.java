package org.purplebean.kmip.benchmark.subjects.model.v2x1.type;

import lombok.Getter;
import org.purplebean.kmip.api.KmipSpec;
import org.purplebean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purplebean.kmip.model.v2x1.type.StreamingCapability;

/**
 * Benchmark subject for {@link StreamingCapability}.
 */
public class StreamingCapabilityBenchmarkSubject extends KmipBenchmarkSubject<StreamingCapability> {

  @Getter
  private final KmipSpec spec = KmipSpec.UnknownVersion;

  /**
   * Constructs a new {@link StreamingCapabilityBenchmarkSubject}.
   */
  public StreamingCapabilityBenchmarkSubject() throws Exception {
    StreamingCapability subject = StreamingCapability.of(true);
    initialize(subject, StreamingCapability.class);
  }

  @Override
  public String name() {
    return "StreamingCapability";
  }
}