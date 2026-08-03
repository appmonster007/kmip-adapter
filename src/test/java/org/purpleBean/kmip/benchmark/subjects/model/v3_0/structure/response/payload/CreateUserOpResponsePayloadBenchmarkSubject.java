package org.purpleBean.kmip.benchmark.subjects.model.v3_0.structure.response.payload;

import lombok.Getter;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purpleBean.kmip.model.core.type.UniqueIdentifier;
import org.purpleBean.kmip.model.v3_0.structure.response.payload.CreateUserOpResponsePayload;

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