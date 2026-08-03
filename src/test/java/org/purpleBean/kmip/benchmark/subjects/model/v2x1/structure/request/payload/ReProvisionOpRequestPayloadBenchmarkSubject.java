package org.purpleBean.kmip.benchmark.subjects.model.v2x1.structure.request.payload;

import lombok.Getter;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purpleBean.kmip.model.core.type.CertificateRequest;
import org.purpleBean.kmip.model.v2x1.structure.request.payload.ReProvisionOpRequestPayload;

public class ReProvisionOpRequestPayloadBenchmarkSubject
    extends KmipBenchmarkSubject<ReProvisionOpRequestPayload> {

  @Getter
  private final KmipSpec spec = KmipSpec.V2_1;

  public ReProvisionOpRequestPayloadBenchmarkSubject() throws Exception {
    ReProvisionOpRequestPayload subject = ReProvisionOpRequestPayload
        .builder()
        .certificateRequest(CertificateRequest.of(new byte[] {0x30, 0x1A, 0x01}))
        .build();
    initialize(subject, ReProvisionOpRequestPayload.class);
  }

  @Override
  public String name() {
    return "ReProvisionOpRequestPayload";
  }
}