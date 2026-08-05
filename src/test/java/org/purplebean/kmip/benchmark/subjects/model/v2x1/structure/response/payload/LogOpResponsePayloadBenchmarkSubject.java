package org.purplebean.kmip.benchmark.subjects.model.v2x1.structure.response.payload;

import lombok.Getter;
import org.purplebean.kmip.api.KmipSpec;
import org.purplebean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purplebean.kmip.model.v2x1.structure.response.payload.LogOpResponsePayload;

/**
 * Benchmark subject for {@link LogOpResponsePayload}.
 */
public class LogOpResponsePayloadBenchmarkSubject
    extends KmipBenchmarkSubject<LogOpResponsePayload> {

  @Getter
  private final KmipSpec spec = KmipSpec.UnknownVersion; // TODO: Adjust if needed

  /**
   * Constructs a new {@link LogOpResponsePayloadBenchmarkSubject}.
   */
  public LogOpResponsePayloadBenchmarkSubject() throws Exception {
    LogOpResponsePayload subject = LogOpResponsePayload
        .builder()
        .build();  // TODO: Create a default instance
    initialize(subject, LogOpResponsePayload.class);
  }

  @Override
  public String name() {
    return "LogOpResponsePayload";
  }
}