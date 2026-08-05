package org.purplebean.kmip.benchmark.subjects.model.v2x1.structure.request.payload;

import lombok.Getter;
import org.purplebean.kmip.api.KmipSpec;
import org.purplebean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purplebean.kmip.model.v2x1.structure.request.payload.LogOpRequestPayload;
import org.purplebean.kmip.model.v2x1.type.LogMessage;

/**
 * Benchmark subject for {@link LogOpRequestPayload}.
 */
public class LogOpRequestPayloadBenchmarkSubject extends KmipBenchmarkSubject<LogOpRequestPayload> {

  @Getter
  private final KmipSpec spec = KmipSpec.V2_1;

  /**
   * Constructs a new {@link LogOpRequestPayloadBenchmarkSubject}.
   */
  public LogOpRequestPayloadBenchmarkSubject() throws Exception {
    LogOpRequestPayload subject = LogOpRequestPayload
        .builder()
        .logMessage(LogMessage
            .builder()
            .value("test-log-message")
            .build())
        .build();
    initialize(subject, LogOpRequestPayload.class);
  }

  @Override
  public String name() {
    return "LogOpRequestPayload";
  }
}