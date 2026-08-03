package org.purpleBean.kmip.benchmark.subjects.model.v1_2.structure.request.payload;

import lombok.Getter;
import org.purpleBean.kmip.api.KmipContext;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purpleBean.kmip.model.core.structure.CommonTemplateAttribute;
import org.purpleBean.kmip.model.core.structure.PrivateKeyTemplateAttribute;
import org.purpleBean.kmip.model.core.structure.PublicKeyTemplateAttribute;
import org.purpleBean.kmip.model.core.type.Offset;
import org.purpleBean.kmip.model.core.type.PrivateKeyUniqueIdentifier;
import org.purpleBean.kmip.model.v1_2.structure.request.payload.ReKeyKeyPairOpRequestPayload;

public class ReKeyKeyPairOpRequestPayloadBenchmarkSubject
    extends KmipBenchmarkSubject<ReKeyKeyPairOpRequestPayload> {

  @Getter
  private final KmipSpec spec = KmipSpec.UnknownVersion;

  public ReKeyKeyPairOpRequestPayloadBenchmarkSubject() throws Exception {
    ReKeyKeyPairOpRequestPayload subject = ReKeyKeyPairOpRequestPayload
        .builder()
        .privateKeyUniqueIdentifier(PrivateKeyUniqueIdentifier
            .builder()
            .value("private-uid")
            .build())
        .offset(Offset
            .builder()
            .value(100)
            .build())
        .commonTemplateAttribute(
            CommonTemplateAttribute.of(java.util.List.of(), java.util.List.of()))
        .privateKeyTemplateAttribute(
            PrivateKeyTemplateAttribute.of(java.util.List.of(), java.util.List.of()))
        .publicKeyTemplateAttribute(
            PublicKeyTemplateAttribute.of(java.util.List.of(), java.util.List.of()))
        .build();
    initialize(subject, ReKeyKeyPairOpRequestPayload.class);
  }

  @Override
  public String name() {
    return "ReKeyKeyPairOpRequestPayload";
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