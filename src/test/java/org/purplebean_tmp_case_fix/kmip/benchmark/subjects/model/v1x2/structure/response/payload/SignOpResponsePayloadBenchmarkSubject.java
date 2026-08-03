package org.purplebean.kmip.benchmark.subjects.model.v1x2.structure.response.payload;

import lombok.Getter;
import org.purplebean.kmip.api.KmipSpec;
import org.purplebean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purplebean.kmip.model.core.type.SignatureData;
import org.purplebean.kmip.model.core.type.UniqueIdentifier;
import org.purplebean.kmip.model.v1x2.structure.response.payload.SignOpResponsePayload;

public class SignOpResponsePayloadBenchmarkSubject
    extends KmipBenchmarkSubject<SignOpResponsePayload> {

  @Getter
  private final KmipSpec spec = KmipSpec.V1_2;

  public SignOpResponsePayloadBenchmarkSubject() throws Exception {
    SignOpResponsePayload subject = SignOpResponsePayload
        .builder()
        .uniqueIdentifier(UniqueIdentifier.of("123e4567-e89b-12d3-a456-426614174000"))
        .signatureData(SignatureData.of(new byte[] {1, 2, 3}))
        .build();
    initialize(subject, SignOpResponsePayload.class);
  }

  @Override
  public String name() {
    return "SignOpResponsePayload";
  }
}
