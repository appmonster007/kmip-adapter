package org.purplebean.kmip.benchmark.subjects.model.v2x1.structure.request.payload;

import lombok.Getter;
import org.purplebean.kmip.api.KmipSpec;
import org.purplebean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purplebean.kmip.model.v2x1.structure.request.payload.RecertifyOpRequestPayload;

public class RecertifyOpRequestPayloadBenchmarkSubject
    extends KmipBenchmarkSubject<RecertifyOpRequestPayload> {

  @Getter
  private final KmipSpec spec = KmipSpec.UnknownVersion;

  public RecertifyOpRequestPayloadBenchmarkSubject() throws Exception {
    RecertifyOpRequestPayload subject = RecertifyOpRequestPayload
        .builder()
        .build();
    initialize(subject, RecertifyOpRequestPayload.class);
  }

  @Override
  public String name() {
    return "RecertifyOpRequestPayload";
  }
}