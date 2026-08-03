package org.purplebean.kmip.benchmark.subjects.model.v3x0.structure.response.payload;

import lombok.Getter;
import org.purplebean.kmip.api.KmipSpec;
import org.purplebean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purplebean.kmip.model.core.type.UniqueIdentifier;
import org.purplebean.kmip.model.v3x0.structure.response.payload.CreateUserOpResponsePayload;

public class CreateUserOpResponsePayloadBenchmarkSubject
    extends KmipBenchmarkSubject<CreateUserOpResponsePayload> {

  @Getter
  private final KmipSpec spec = KmipSpec.V3_0;

  public CreateUserOpResponsePayloadBenchmarkSubject() throws Exception {
    CreateUserOpResponsePayload subject = CreateUserOpResponsePayload
        .builder()
        .uniqueIdentifier(UniqueIdentifier
            .builder()
            .value("test-uid-1")
            .build())
        .build();
    initialize(subject, CreateUserOpResponsePayload.class);
  }

  @Override
  public String name() {
    return "CreateUserOpResponsePayload";
  }
}