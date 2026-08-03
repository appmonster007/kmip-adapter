package org.purpleBean.kmip.benchmark.subjects.model.v1x2.structure.response.payload;

import lombok.Getter;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purpleBean.kmip.model.core.enumeration.ValidityIndicator;
import org.purpleBean.kmip.model.v1x2.structure.response.payload.ValidateOpResponsePayload;

public class ValidateOpResponsePayloadBenchmarkSubject
    extends KmipBenchmarkSubject<ValidateOpResponsePayload> {

  @Getter
  private final KmipSpec spec = KmipSpec.V1_2;

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
