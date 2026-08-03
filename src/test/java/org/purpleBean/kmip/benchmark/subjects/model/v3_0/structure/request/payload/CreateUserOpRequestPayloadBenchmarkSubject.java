package org.purpleBean.kmip.benchmark.subjects.model.v3_0.structure.request.payload;

import lombok.Getter;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purpleBean.kmip.model.v2_1.structure.Attributes;
import org.purpleBean.kmip.model.v3_0.structure.request.payload.CreateUserOpRequestPayload;

public class CreateUserOpRequestPayloadBenchmarkSubject
    extends KmipBenchmarkSubject<CreateUserOpRequestPayload> {

  @Getter
  private final KmipSpec spec = KmipSpec.V3_0;

  public CreateUserOpRequestPayloadBenchmarkSubject() throws Exception {
    CreateUserOpRequestPayload subject = CreateUserOpRequestPayload
        .builder()
        .attributes(Attributes.of(java.util.List.of()))
        .build();
    initialize(subject, CreateUserOpRequestPayload.class);
  }

  @Override
  public String name() {
    return "CreateUserOpRequestPayload";
  }
}