package org.purpleBean.kmip.benchmark.subjects.model.v2x1.structure.response.payload;

import lombok.Getter;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purpleBean.kmip.model.core.type.UniqueIdentifier;
import org.purpleBean.kmip.model.v2x1.structure.response.payload.SetAttributeOpResponsePayload;

public class SetAttributeOpResponsePayloadBenchmarkSubject
    extends KmipBenchmarkSubject<SetAttributeOpResponsePayload> {

  @Getter
  private final KmipSpec spec = KmipSpec.V2_1;

  public SetAttributeOpResponsePayloadBenchmarkSubject() throws Exception {
    SetAttributeOpResponsePayload subject = SetAttributeOpResponsePayload.of(UniqueIdentifier
        .builder()
        .value("test-uid-1")
        .build());
    initialize(subject, SetAttributeOpResponsePayload.class);
  }

  @Override
  public String name() {
    return "SetAttributeOpResponsePayload";
  }
}