package org.purplebean.kmip.benchmark.subjects.model.v2x1.structure.response.payload;

import lombok.Getter;
import org.purplebean.kmip.api.KmipSpec;
import org.purplebean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purplebean.kmip.model.core.type.PrivateKeyUniqueIdentifier;
import org.purplebean.kmip.model.core.type.PublicKeyUniqueIdentifier;
import org.purplebean.kmip.model.v2x1.structure.response.payload.CreateKeyPairOpResponsePayload;

/**
 * Benchmark subject for {@link CreateKeyPairOpResponsePayload}.
 */
public class CreateKeyPairOpResponsePayloadBenchmarkSubject
    extends KmipBenchmarkSubject<CreateKeyPairOpResponsePayload> {

  @Getter
  private final KmipSpec spec = KmipSpec.V2_1;

  /**
   * Constructs a new {@link CreateKeyPairOpResponsePayloadBenchmarkSubject}.
   */
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
