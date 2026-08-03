package org.purplebean.kmip.benchmark.subjects.model.v2x1.structure.request.payload;

import lombok.Getter;
import org.purplebean.kmip.api.KmipSpec;
import org.purplebean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purplebean.kmip.model.v2x1.structure.request.payload.CertifyOpRequestPayload;

public class CertifyOpRequestPayloadBenchmarkSubject
    extends KmipBenchmarkSubject<CertifyOpRequestPayload> {

  @Getter
  private final KmipSpec spec = KmipSpec.UnknownVersion;

  public CertifyOpRequestPayloadBenchmarkSubject() throws Exception {
    CertifyOpRequestPayload subject = CertifyOpRequestPayload
        .builder()
        .build();
    initialize(subject, CertifyOpRequestPayload.class);
  }

  @Override
  public String name() {
    return "CertifyOpRequestPayload";
  }
}