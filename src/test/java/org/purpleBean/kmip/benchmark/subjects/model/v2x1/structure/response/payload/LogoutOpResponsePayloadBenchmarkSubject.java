package org.purplebean.kmip.benchmark.subjects.model.v2x1.structure.response.payload;

import lombok.Getter;
import org.purplebean.kmip.api.KmipSpec;
import org.purplebean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purplebean.kmip.model.v2x1.structure.response.payload.LogoutOpResponsePayload;

public class LogoutOpResponsePayloadBenchmarkSubject
    extends KmipBenchmarkSubject<LogoutOpResponsePayload> {

  @Getter
  private final KmipSpec spec = KmipSpec.UnknownVersion; // TODO: Adjust if needed

  public LogoutOpResponsePayloadBenchmarkSubject() throws Exception {
    LogoutOpResponsePayload subject = LogoutOpResponsePayload
        .builder()
        .build();  // TODO: Create a default instance
    initialize(subject, LogoutOpResponsePayload.class);
  }

  @Override
  public String name() {
    return "LogoutOpResponsePayload";
  }
}