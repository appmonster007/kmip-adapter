package org.purplebean.kmip.benchmark.subjects.model.core.structure.response;

import lombok.Getter;
import org.purplebean.kmip.api.KmipSpec;
import org.purplebean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purplebean.kmip.model.core.structure.response.SimpleResponsePayload;

/**
 * Benchmark subject for {@link SimpleResponsePayload}.
 */
public class SimpleResponsePayloadBenchmarkSubject
    extends KmipBenchmarkSubject<SimpleResponsePayload> {

  @Getter
  private final KmipSpec spec = KmipSpec.UnknownVersion;

  /**
   * Constructs a new {@link SimpleResponsePayloadBenchmarkSubject}.
   */
  public SimpleResponsePayloadBenchmarkSubject() throws Exception {
    SimpleResponsePayload subject = SimpleResponsePayload
        .builder()
        .build();
    initialize(subject, SimpleResponsePayload.class);
  }

  @Override
  public String name() {
    return "SimpleResponsePayload";
  }
}
