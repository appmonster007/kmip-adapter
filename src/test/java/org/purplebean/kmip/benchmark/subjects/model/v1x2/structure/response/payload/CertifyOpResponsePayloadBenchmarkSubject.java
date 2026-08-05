package org.purplebean.kmip.benchmark.subjects.model.v1x2.structure.response.payload;

import lombok.Getter;
import org.purplebean.kmip.api.KmipContext;
import org.purplebean.kmip.api.KmipSpec;
import org.purplebean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purplebean.kmip.model.core.structure.TemplateAttribute;
import org.purplebean.kmip.model.core.type.UniqueIdentifier;
import org.purplebean.kmip.model.v1x2.structure.response.payload.CertifyOpResponsePayload;

/**
 * Benchmark subject for {@link CertifyOpResponsePayload}.
 */
public class CertifyOpResponsePayloadBenchmarkSubject
    extends KmipBenchmarkSubject<CertifyOpResponsePayload> {

  @Getter
  private final KmipSpec spec = KmipSpec.UnknownVersion;

  /**
   * Constructs a new {@link CertifyOpResponsePayloadBenchmarkSubject}.
   */
  public CertifyOpResponsePayloadBenchmarkSubject() throws Exception {
    CertifyOpResponsePayload subject = CertifyOpResponsePayload
        .builder()
        .uniqueIdentifier(UniqueIdentifier
            .builder()
            .value("uid")
            .build())
        .templateAttribute(TemplateAttribute.of(java.util.List.of(), java.util.List.of()))
        .build();
    initialize(subject, CertifyOpResponsePayload.class);
  }

  @Override
  public String name() {
    return "CertifyOpResponsePayload";
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