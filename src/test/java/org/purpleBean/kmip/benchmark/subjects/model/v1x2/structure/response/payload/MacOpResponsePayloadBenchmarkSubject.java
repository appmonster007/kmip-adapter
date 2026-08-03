package org.purplebean.kmip.benchmark.subjects.model.v1x2.structure.response.payload;

import lombok.Getter;
import org.purplebean.kmip.api.KmipSpec;
import org.purplebean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purplebean.kmip.model.core.type.MacData;
import org.purplebean.kmip.model.core.type.UniqueIdentifier;
import org.purplebean.kmip.model.v1x2.structure.response.payload.MacOpResponsePayload;

public class MacOpResponsePayloadBenchmarkSubject
    extends KmipBenchmarkSubject<MacOpResponsePayload> {

  @Getter
  private final KmipSpec spec = KmipSpec.V1_2;

  public MacOpResponsePayloadBenchmarkSubject() throws Exception {
    MacOpResponsePayload subject = MacOpResponsePayload
        .builder()
        .uniqueIdentifier(UniqueIdentifier.of("123e4567-e89b-12d3-a456-426614174000"))
        .macData(MacData.of(new byte[] {1, 2, 3}))
        .build();
    initialize(subject, MacOpResponsePayload.class);
  }

  @Override
  public String name() {
    return "MacOpResponsePayload";
  }
}
