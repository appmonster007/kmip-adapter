package org.purpleBean.kmip.benchmark.subjects.model.v2_1.type;

import lombok.Getter;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purpleBean.kmip.model.v2_1.type.LogMessage;

public class LogMessageBenchmarkSubject extends KmipBenchmarkSubject<LogMessage> {

  @Getter
  private final KmipSpec spec = KmipSpec.V2_1; // TODO: Adjust if needed

  public LogMessageBenchmarkSubject() throws Exception {
    LogMessage subject = LogMessage.of("default-string");  // TODO: Create a default instance
    initialize(subject, LogMessage.class);
  }

  @Override
  public String name() {
    return "LogMessage";
  }
}