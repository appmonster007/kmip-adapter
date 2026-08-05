package org.purplebean.kmip.benchmark.subjects.model.v1x2.structure.response.payload;

import lombok.Getter;
import org.purplebean.kmip.api.KmipSpec;
import org.purplebean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purplebean.kmip.model.core.enumeration.ValidityIndicator;
import org.purplebean.kmip.model.core.type.UniqueIdentifier;
import org.purplebean.kmip.model.v1x2.structure.response.payload.MacVerifyOpResponsePayload;

/**
 * Benchmark subject for {@link MacVerifyOpResponsePayload}.
 */
public class MacVerifyOpResponsePayloadBenchmarkSubject
    extends KmipBenchmarkSubject<MacVerifyOpResponsePayload> {

  @Getter
  private final KmipSpec spec = KmipSpec.V1_2;

  /**
   * Constructs a new {@link MacVerifyOpResponsePayloadBenchmarkSubject}.
   */
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
