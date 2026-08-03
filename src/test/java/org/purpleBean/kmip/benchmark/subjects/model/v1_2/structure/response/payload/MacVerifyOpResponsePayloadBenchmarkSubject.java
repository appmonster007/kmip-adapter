package org.purpleBean.kmip.benchmark.subjects.model.v1_2.structure.response.payload;

import lombok.Getter;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purpleBean.kmip.model.core.enumeration.ValidityIndicator;
import org.purpleBean.kmip.model.core.type.UniqueIdentifier;
import org.purpleBean.kmip.model.v1_2.structure.response.payload.MacVerifyOpResponsePayload;

public class MacVerifyOpResponsePayloadBenchmarkSubject
    extends KmipBenchmarkSubject<MacVerifyOpResponsePayload> {

  @Getter
  private final KmipSpec spec = KmipSpec.V1_2;

  public MacVerifyOpResponsePayloadBenchmarkSubject() throws Exception {
    MacVerifyOpResponsePayload subject = MacVerifyOpResponsePayload
        .builder()
        .uniqueIdentifier(UniqueIdentifier.of("123e4567-e89b-12d3-a456-426614174000"))
        .validityIndicator(ValidityIndicator.of(ValidityIndicator.Standard.VALID))
        .build();
    initialize(subject, MacVerifyOpResponsePayload.class);
  }

  @Override
  public String name() {
    return "MacVerifyOpResponsePayload";
  }
}
