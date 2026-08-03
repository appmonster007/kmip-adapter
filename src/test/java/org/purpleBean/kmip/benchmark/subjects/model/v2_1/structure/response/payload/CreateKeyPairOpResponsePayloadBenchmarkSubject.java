package org.purpleBean.kmip.benchmark.subjects.model.v2_1.structure.response.payload;

import lombok.Getter;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purpleBean.kmip.model.core.type.PrivateKeyUniqueIdentifier;
import org.purpleBean.kmip.model.core.type.PublicKeyUniqueIdentifier;
import org.purpleBean.kmip.model.v2_1.structure.response.payload.CreateKeyPairOpResponsePayload;

public class CreateKeyPairOpResponsePayloadBenchmarkSubject
    extends KmipBenchmarkSubject<CreateKeyPairOpResponsePayload> {

  @Getter
  private final KmipSpec spec = KmipSpec.V2_1;

  public CreateKeyPairOpResponsePayloadBenchmarkSubject() throws Exception {
    CreateKeyPairOpResponsePayload subject = CreateKeyPairOpResponsePayload
        .builder()
        .privateKeyUniqueIdentifier(PrivateKeyUniqueIdentifier.of("priv"))
        .publicKeyUniqueIdentifier(PublicKeyUniqueIdentifier.of("pub"))
        .build();
    initialize(subject, CreateKeyPairOpResponsePayload.class);
  }

  @Override
  public String name() {
    return "CreateKeyPairOpResponsePayload";
  }
}
