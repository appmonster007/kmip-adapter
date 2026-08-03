package org.purplebean.kmip.benchmark.subjects.model.v3x0.structure.response.payload;

import lombok.Getter;
import org.purplebean.kmip.api.KmipSpec;
import org.purplebean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purplebean.kmip.model.core.type.UniqueIdentifier;
import org.purplebean.kmip.model.v3x0.structure.response.payload.DeactivateOpResponsePayload;

public class DeactivateOpResponsePayloadBenchmarkSubject
    extends KmipBenchmarkSubject<DeactivateOpResponsePayload> {

  @Getter
  private final KmipSpec spec = KmipSpec.V3_0;

  public DeactivateOpResponsePayloadBenchmarkSubject() throws Exception {
    DeactivateOpResponsePayload subject = DeactivateOpResponsePayload
        .builder()
        .uniqueIdentifier(UniqueIdentifier
            .builder()
            .value("test-uid-1")
            .build())
        .build();
    initialize(subject, DeactivateOpResponsePayload.class);
  }

  @Override
  public String name() {
    return "DeactivateOpResponsePayload";
  }
}