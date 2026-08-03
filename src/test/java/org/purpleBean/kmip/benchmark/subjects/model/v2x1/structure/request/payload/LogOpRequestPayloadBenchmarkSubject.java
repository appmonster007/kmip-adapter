package org.purpleBean.kmip.benchmark.subjects.model.v2x1.structure.request.payload;

import lombok.Getter;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purpleBean.kmip.model.v2x1.structure.request.payload.LogOpRequestPayload;
import org.purpleBean.kmip.model.v2x1.type.LogMessage;

public class LogOpRequestPayloadBenchmarkSubject extends KmipBenchmarkSubject<LogOpRequestPayload> {

  @Getter
  private final KmipSpec spec = KmipSpec.V2_1;

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