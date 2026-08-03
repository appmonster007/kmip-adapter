package org.purplebean.kmip.benchmark.subjects.model.v1x2.structure.response.payload;

import lombok.Getter;
import org.purplebean.kmip.api.KmipSpec;
import org.purplebean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purplebean.kmip.model.v1x2.structure.response.payload.NotifyOpResponsePayload;

public class NotifyOpResponsePayloadBenchmarkSubject
    extends KmipBenchmarkSubject<NotifyOpResponsePayload> {

  @Getter
  private final KmipSpec spec = KmipSpec.V1_2;

  public NotifyOpResponsePayloadBenchmarkSubject() throws Exception {
    NotifyOpResponsePayload subject = NotifyOpResponsePayload
        .builder()
        .build();
    initialize(subject, NotifyOpResponsePayload.class);
  }

  @Override
  public String name() {
    return "NotifyOpResponsePayload";
  }
}
