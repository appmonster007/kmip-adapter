package org.purplebean.kmip.benchmark.subjects.model.v1x2.structure.response.payload;

import lombok.Getter;
import org.purplebean.kmip.api.KmipContext;
import org.purplebean.kmip.api.KmipSpec;
import org.purplebean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purplebean.kmip.model.core.structure.PrivateKeyTemplateAttribute;
import org.purplebean.kmip.model.core.structure.PublicKeyTemplateAttribute;
import org.purplebean.kmip.model.core.type.PrivateKeyUniqueIdentifier;
import org.purplebean.kmip.model.core.type.PublicKeyUniqueIdentifier;
import org.purplebean.kmip.model.v1x2.structure.response.payload.CreateKeyPairOpResponsePayload;

/**
 * Benchmark subject for {@link CreateKeyPairOpResponsePayload}.
 */
public class CreateKeyPairOpResponsePayloadBenchmarkSubject
    extends KmipBenchmarkSubject<CreateKeyPairOpResponsePayload> {

  @Getter
  private final KmipSpec spec = KmipSpec.UnknownVersion;

  /**
   * Constructs a new {@link CreateKeyPairOpResponsePayloadBenchmarkSubject}.
   */
  public CreateKeyPairOpResponsePayloadBenchmarkSubject() throws Exception {
    CreateKeyPairOpResponsePayload subject = CreateKeyPairOpResponsePayload
        .builder()
        .privateKeyUniqueIdentifier(PrivateKeyUniqueIdentifier
            .builder()
            .value("private-uid")
            .build())
        .publicKeyUniqueIdentifier(PublicKeyUniqueIdentifier
            .builder()
            .value("public-uid")
            .build())
        .privateKeyTemplateAttribute(
            PrivateKeyTemplateAttribute.of(java.util.List.of(), java.util.List.of()))
        .publicKeyTemplateAttribute(
            PublicKeyTemplateAttribute.of(java.util.List.of(), java.util.List.of()))
        .build();
    initialize(subject, CreateKeyPairOpResponsePayload.class);
  }

  @Override
  public String name() {
    return "CreateKeyPairOpResponsePayload";
  }

  @Override
  public void setup() throws Exception {
    KmipContext.setSpec(spec);
  }

  @Override
  public void tearDown() {
    KmipContext.clear();
  }
}