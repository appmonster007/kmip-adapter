package org.purplebean.kmip.benchmark.subjects.model.v2x1.structure.response.payload;

import lombok.Getter;
import org.purplebean.kmip.api.KmipSpec;
import org.purplebean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purplebean.kmip.model.core.type.UniqueIdentifier;
import org.purplebean.kmip.model.v2x1.structure.response.payload.ReProvisionOpResponsePayload;

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