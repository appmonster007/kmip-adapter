package org.purplebean.kmip.benchmark.subjects.model.v2x1.structure.response.payload;

import lombok.Getter;
import org.purplebean.kmip.api.KmipSpec;
import org.purplebean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purplebean.kmip.model.core.type.UniqueIdentifier;
import org.purplebean.kmip.model.v2x1.structure.response.payload.AddAttributeOpResponsePayload;

public class AddAttributeOpResponsePayloadBenchmarkSubject
    extends KmipBenchmarkSubject<AddAttributeOpResponsePayload> {

  @Getter
  private final KmipSpec spec = KmipSpec.V2_1;

  public AddAttributeOpResponsePayloadBenchmarkSubject() throws Exception {
    AddAttributeOpResponsePayload subject = AddAttributeOpResponsePayload
        .builder()
        .uniqueIdentifier(UniqueIdentifier.of("test-uid"))
        .build();
    initialize(subject, AddAttributeOpResponsePayload.class);
  }

  @Override
  public String name() {
    return "AddAttributeOpResponsePayload";
  }
}
