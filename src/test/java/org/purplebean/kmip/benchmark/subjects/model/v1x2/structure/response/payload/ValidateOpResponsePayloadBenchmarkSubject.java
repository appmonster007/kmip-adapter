package org.purplebean.kmip.benchmark.subjects.model.v1x2.structure.response.payload;

import lombok.Getter;
import org.purplebean.kmip.api.KmipSpec;
import org.purplebean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purplebean.kmip.model.core.enumeration.ValidityIndicator;
import org.purplebean.kmip.model.v1x2.structure.response.payload.ValidateOpResponsePayload;

/**
 * Benchmark subject for {@link ValidateOpResponsePayload}.
 */
public class ValidateOpResponsePayloadBenchmarkSubject
    extends KmipBenchmarkSubject<ValidateOpResponsePayload> {

  @Getter
  private final KmipSpec spec = KmipSpec.V1_2;

  /**
   * Constructs a new {@link ValidateOpResponsePayloadBenchmarkSubject}.
   */
  public ValidateOpResponsePayloadBenchmarkSubject() throws Exception {
    ValidateOpResponsePayload subject = ValidateOpResponsePayload
        .builder()
        .validityIndicator(ValidityIndicator.of(ValidityIndicator.Standard.VALID))
        .build();
    initialize(subject, ValidateOpResponsePayload.class);
  }

  @Override
  public String name() {
    return "ValidateOpResponsePayload";
  }
}
