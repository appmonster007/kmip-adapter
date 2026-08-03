package org.purpleBean.kmip.benchmark.subjects.model.v3_0.structure.response.payload;

import lombok.Getter;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purpleBean.kmip.model.core.type.UniqueIdentifier;
import org.purpleBean.kmip.model.v3_0.structure.response.payload.CreateGroupOpResponsePayload;

public class CreateGroupOpResponsePayloadBenchmarkSubject
    extends KmipBenchmarkSubject<CreateGroupOpResponsePayload> {

  @Getter
  private final KmipSpec spec = KmipSpec.V3_0;

  public CreateGroupOpResponsePayloadBenchmarkSubject() throws Exception {
    CreateGroupOpResponsePayload subject = CreateGroupOpResponsePayload
        .builder()
        .uniqueIdentifier(UniqueIdentifier
            .builder()
            .value("test-uid-1")
            .build())
        .build();
    initialize(subject, CreateGroupOpResponsePayload.class);
  }

  @Override
  public String name() {
    return "CreateGroupOpResponsePayload";
  }
}