package org.purpleBean.kmip.benchmark.subjects.model.v1_2.structure.response.payload;

import lombok.Getter;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purpleBean.kmip.model.v1_2.structure.response.payload.NotifyOpResponsePayload;

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
