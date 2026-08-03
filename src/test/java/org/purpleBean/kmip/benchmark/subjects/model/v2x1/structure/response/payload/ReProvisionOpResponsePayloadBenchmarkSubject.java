package org.purpleBean.kmip.benchmark.subjects.model.v2x1.structure.response.payload;

import lombok.Getter;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purpleBean.kmip.model.core.type.UniqueIdentifier;
import org.purpleBean.kmip.model.v2x1.structure.response.payload.ReProvisionOpResponsePayload;

public class ReProvisionOpResponsePayloadBenchmarkSubject
    extends KmipBenchmarkSubject<ReProvisionOpResponsePayload> {

  @Getter
  private final KmipSpec spec = KmipSpec.V2_1;

  public ReProvisionOpResponsePayloadBenchmarkSubject() throws Exception {
    ReProvisionOpResponsePayload subject = ReProvisionOpResponsePayload
        .builder()
        .uniqueIdentifier(UniqueIdentifier
            .builder()
            .value("reprovision-uid-1")
            .build())
        .build();
    initialize(subject, ReProvisionOpResponsePayload.class);
  }

  @Override
  public String name() {
    return "ReProvisionOpResponsePayload";
  }
}