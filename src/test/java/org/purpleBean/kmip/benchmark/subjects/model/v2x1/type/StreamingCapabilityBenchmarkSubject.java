package org.purpleBean.kmip.benchmark.subjects.model.v2x1.type;

import lombok.Getter;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purpleBean.kmip.model.v2x1.type.StreamingCapability;

public class StreamingCapabilityBenchmarkSubject extends KmipBenchmarkSubject<StreamingCapability> {

  @Getter
  private final KmipSpec spec = KmipSpec.UnknownVersion;

  public StreamingCapabilityBenchmarkSubject() throws Exception {
    StreamingCapability subject = StreamingCapability.of(true);
    initialize(subject, StreamingCapability.class);
  }

  @Override
  public String name() {
    return "StreamingCapability";
  }
}