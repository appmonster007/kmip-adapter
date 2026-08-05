package org.purplebean.kmip.benchmark.subjects.model.v1x2.structure.request.payload;

import lombok.Getter;
import org.purplebean.kmip.api.KmipContext;
import org.purplebean.kmip.api.KmipSpec;
import org.purplebean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purplebean.kmip.model.core.structure.CommonTemplateAttribute;
import org.purplebean.kmip.model.core.structure.PrivateKeyTemplateAttribute;
import org.purplebean.kmip.model.core.structure.PublicKeyTemplateAttribute;
import org.purplebean.kmip.model.v1x2.structure.request.payload.CreateKeyPairOpRequestPayload;

/**
 * Benchmark subject for {@link CreateKeyPairOpRequestPayload}.
 */
public class CreateKeyPairOpRequestPayloadBenchmarkSubject
    extends KmipBenchmarkSubject<CreateKeyPairOpRequestPayload> {

  @Getter
  private final KmipSpec spec = KmipSpec.UnknownVersion;

  /**
   * Constructs a new {@link CreateKeyPairOpRequestPayloadBenchmarkSubject}.
   */
  public CreateKeyPairOpRequestPayloadBenchmarkSubject() throws Exception {
    CreateKeyPairOpRequestPayload subject = CreateKeyPairOpRequestPayload
        .builder()
        .commonTemplateAttribute(
            CommonTemplateAttribute.of(java.util.List.of(), java.util.List.of()))
        .privateKeyTemplateAttribute(
            PrivateKeyTemplateAttribute.of(java.util.List.of(), java.util.List.of()))
        .publicKeyTemplateAttribute(
            PublicKeyTemplateAttribute.of(java.util.List.of(), java.util.List.of()))
        .build();
    initialize(subject, CreateKeyPairOpRequestPayload.class);
  }

  @Override
  public String name() {
    return "CreateKeyPairOpRequestPayload";
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