package org.purpleBean.kmip.benchmark.subjects.model.v2_1.structure.request.payload;

import lombok.Getter;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purpleBean.kmip.model.v2_1.structure.request.payload.CreateKeyPairOpRequestPayload;

public class CreateKeyPairOpRequestPayloadBenchmarkSubject
    extends KmipBenchmarkSubject<CreateKeyPairOpRequestPayload> {

  @Getter
  private final KmipSpec spec = KmipSpec.UnknownVersion;

  public CreateKeyPairOpRequestPayloadBenchmarkSubject() throws Exception {
    CreateKeyPairOpRequestPayload subject = CreateKeyPairOpRequestPayload
        .builder()
        .build();
    initialize(subject, CreateKeyPairOpRequestPayload.class);
  }

  @Override
  public String name() {
    return "CreateKeyPairOpRequestPayload";
  }
}