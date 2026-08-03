package org.purpleBean.kmip.benchmark.subjects.model.v3x0.structure.response.payload;

import lombok.Getter;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purpleBean.kmip.model.core.type.UniqueIdentifier;
import org.purpleBean.kmip.model.v3x0.structure.response.payload.CreateCredentialOpResponsePayload;

public class CreateCredentialOpResponsePayloadBenchmarkSubject
    extends KmipBenchmarkSubject<CreateCredentialOpResponsePayload> {

  @Getter
  private final KmipSpec spec = KmipSpec.V3_0;

  public CreateCredentialOpResponsePayloadBenchmarkSubject() throws Exception {
    CreateCredentialOpResponsePayload subject = CreateCredentialOpResponsePayload
        .builder()
        .uniqueIdentifier(UniqueIdentifier
            .builder()
            .value("test-uid-1")
            .build())
        .build();
    initialize(subject, CreateCredentialOpResponsePayload.class);
  }

  @Override
  public String name() {
    return "CreateCredentialOpResponsePayload";
  }
}