package org.purplebean.kmip.benchmark.subjects.model.v1x2.structure.response.payload;

import lombok.Getter;
import org.purplebean.kmip.api.KmipSpec;
import org.purplebean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purplebean.kmip.model.core.enumeration.ValidityIndicator;
import org.purplebean.kmip.model.core.type.UniqueIdentifier;
import org.purplebean.kmip.model.v1x2.structure.response.payload.SignatureVerifyOpResponsePayload;

public class SignatureVerifyOpResponsePayloadBenchmarkSubject
    extends KmipBenchmarkSubject<SignatureVerifyOpResponsePayload> {

  @Getter
  private final KmipSpec spec = KmipSpec.V1_2;

  public SignatureVerifyOpResponsePayloadBenchmarkSubject() throws Exception {
    SignatureVerifyOpResponsePayload subject = SignatureVerifyOpResponsePayload
        .builder()
        .uniqueIdentifier(UniqueIdentifier.of("123e4567-e89b-12d3-a456-426614174000"))
        .validityIndicator(ValidityIndicator.of(ValidityIndicator.Standard.VALID))
        .build();
    initialize(subject, SignatureVerifyOpResponsePayload.class);
  }

  @Override
  public String name() {
    return "SignatureVerifyOpResponsePayload";
  }
}
