package org.purplebean.kmip.benchmark.subjects.model.v2x1.structure.request.payload;

import lombok.Getter;
import org.purplebean.kmip.api.KmipSpec;
import org.purplebean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purplebean.kmip.model.v2x1.structure.request.payload.LocateOpRequestPayload;

public class LocateOpRequestPayloadBenchmarkSubject
    extends KmipBenchmarkSubject<LocateOpRequestPayload> {

  @Getter
  private final KmipSpec spec = KmipSpec.UnknownVersion;

  public LocateOpRequestPayloadBenchmarkSubject() throws Exception {
    LocateOpRequestPayload subject = LocateOpRequestPayload
        .builder()
        .build();
    initialize(subject, LocateOpRequestPayload.class);
  }

  @Override
  public String name() {
    return "LocateOpRequestPayload";
  }
}