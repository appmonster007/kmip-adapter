package org.purpleBean.kmip.benchmark.subjects.model.v3_0.structure.request.payload;

import lombok.Getter;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purpleBean.kmip.model.v2_1.structure.Attributes;
import org.purpleBean.kmip.model.v3_0.structure.request.payload.CreateGroupOpRequestPayload;

public class CreateGroupOpRequestPayloadBenchmarkSubject
    extends KmipBenchmarkSubject<CreateGroupOpRequestPayload> {

  @Getter
  private final KmipSpec spec = KmipSpec.V3_0;

  public CreateGroupOpRequestPayloadBenchmarkSubject() throws Exception {
    CreateGroupOpRequestPayload subject = CreateGroupOpRequestPayload
        .builder()
        .attributes(Attributes.of(java.util.List.of()))
        .build();
    initialize(subject, CreateGroupOpRequestPayload.class);
  }

  @Override
  public String name() {
    return "CreateGroupOpRequestPayload";
  }
}