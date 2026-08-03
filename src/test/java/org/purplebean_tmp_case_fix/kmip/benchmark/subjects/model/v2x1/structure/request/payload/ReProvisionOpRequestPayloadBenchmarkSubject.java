package org.purplebean.kmip.benchmark.subjects.model.v2x1.structure.request.payload;

import lombok.Getter;
import org.purplebean.kmip.api.KmipSpec;
import org.purplebean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purplebean.kmip.model.core.type.CertificateRequest;
import org.purplebean.kmip.model.v2x1.structure.request.payload.ReProvisionOpRequestPayload;

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