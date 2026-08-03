package org.purplebean.kmip.benchmark.subjects.model.v3x0.structure.request.payload;

import lombok.Getter;
import org.purplebean.kmip.api.KmipSpec;
import org.purplebean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purplebean.kmip.model.core.type.UniqueIdentifier;
import org.purplebean.kmip.model.v3x0.structure.request.payload.DeactivateOpRequestPayload;

public class DeactivateOpRequestPayloadBenchmarkSubject
    extends KmipBenchmarkSubject<DeactivateOpRequestPayload> {

  @Getter
  private final KmipSpec spec = KmipSpec.V3_0;

  public DeactivateOpRequestPayloadBenchmarkSubject() throws Exception {
    DeactivateOpRequestPayload subject = DeactivateOpRequestPayload
        .builder()
        .uniqueIdentifier(UniqueIdentifier
            .builder()
            .value("test-uid-1")
            .build())
        .build();
    initialize(subject, DeactivateOpRequestPayload.class);
  }

  @Override
  public String name() {
    return "DeactivateOpRequestPayload";
  }
}