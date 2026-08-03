package org.purpleBean.kmip.benchmark.subjects.model.v2x1.structure.request.payload;

import lombok.Getter;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purpleBean.kmip.model.v2x1.structure.request.payload.CertifyOpRequestPayload;

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