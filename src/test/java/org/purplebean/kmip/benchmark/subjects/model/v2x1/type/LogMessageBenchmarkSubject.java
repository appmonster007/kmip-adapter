package org.purplebean.kmip.benchmark.subjects.model.v2x1.type;

import lombok.Getter;
import org.purplebean.kmip.api.KmipSpec;
import org.purplebean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purplebean.kmip.model.v2x1.type.LogMessage;

/**
 * Benchmark subject for {@link LogMessage}.
 */
public class LogMessageBenchmarkSubject extends KmipBenchmarkSubject<LogMessage> {

  @Getter
  private final KmipSpec spec = KmipSpec.V2_1; // TODO: Adjust if needed

  /**
   * Constructs a new {@link LogMessageBenchmarkSubject}.
   */
  public LogMessageBenchmarkSubject() throws Exception {
    LogMessage subject = LogMessage.of("default-string");  // TODO: Create a default instance
    initialize(subject, LogMessage.class);
  }

  @Override
  public String name() {
    return "LogMessage";
  }
}