package org.purpleBean.kmip.benchmark.subjects.model.v1_2.structure.request.payload;

import lombok.Getter;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purpleBean.kmip.model.core.type.DataByteString;
import org.purpleBean.kmip.model.core.type.MacData;
import org.purpleBean.kmip.model.core.type.UniqueIdentifier;
import org.purpleBean.kmip.model.v1_2.structure.request.payload.MacVerifyOpRequestPayload;

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
