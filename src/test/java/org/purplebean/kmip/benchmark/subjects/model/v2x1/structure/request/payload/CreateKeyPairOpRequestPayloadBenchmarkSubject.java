package org.purplebean.kmip.benchmark.subjects.model.v2x1.structure.request.payload;

import lombok.Getter;
import org.purplebean.kmip.api.KmipSpec;
import org.purplebean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purplebean.kmip.model.v2x1.structure.request.payload.CreateKeyPairOpRequestPayload;

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