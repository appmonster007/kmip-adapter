package org.purplebean.kmip.benchmark.subjects.model.v1x2.structure.request.payload;

import lombok.Getter;
import org.purplebean.kmip.api.KmipSpec;
import org.purplebean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purplebean.kmip.model.core.type.DataByteString;
import org.purplebean.kmip.model.core.type.MacData;
import org.purplebean.kmip.model.core.type.UniqueIdentifier;
import org.purplebean.kmip.model.v1x2.structure.request.payload.MacVerifyOpRequestPayload;

public class MacVerifyOpRequestPayloadBenchmarkSubject
    extends KmipBenchmarkSubject<MacVerifyOpRequestPayload> {

  @Getter
  private final KmipSpec spec = KmipSpec.V1_2;

  public MacVerifyOpRequestPayloadBenchmarkSubject() throws Exception {
    MacVerifyOpRequestPayload subject = MacVerifyOpRequestPayload
        .builder()
        .uniqueIdentifier(UniqueIdentifier.of("123e4567-e89b-12d3-a456-426614174000"))
        .data(DataByteString.of(new byte[] {1, 2, 3}))
        .macData(MacData.of(new byte[] {4, 5, 6}))
        .build();
    initialize(subject, MacVerifyOpRequestPayload.class);
  }

  @Override
  public String name() {
    return "MacVerifyOpRequestPayload";
  }
}
