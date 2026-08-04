package org.purplebean.kmip.benchmark.subjects.model.v2x1.structure.response.payload;

import lombok.Getter;
import org.purplebean.kmip.api.KmipSpec;
import org.purplebean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purplebean.kmip.model.core.type.UniqueIdentifier;
import org.purplebean.kmip.model.v2x1.structure.response.payload.ModifyAttributeOpResponsePayload;

public class ModifyAttributeOpResponsePayloadBenchmarkSubject
    extends KmipBenchmarkSubject<ModifyAttributeOpResponsePayload> {

  @Getter
  private final KmipSpec spec = KmipSpec.V2_1;

  public ModifyAttributeOpResponsePayloadBenchmarkSubject() throws Exception {
    ModifyAttributeOpResponsePayload subject = ModifyAttributeOpResponsePayload
        .builder()
        .uniqueIdentifier(UniqueIdentifier.of("test-uid"))
        .build();
    initialize(subject, ModifyAttributeOpResponsePayload.class);
  }

  @Override
  public String name() {
    return "ModifyAttributeOpResponsePayload";
  }
}
