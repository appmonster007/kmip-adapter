package org.purpleBean.kmip.benchmark.subjects.model.v2_1.structure.response.payload;

import lombok.Getter;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purpleBean.kmip.model.core.type.UniqueIdentifier;
import org.purpleBean.kmip.model.v2_1.structure.response.payload.SignatureVerifyOpResponsePayload;

public class SignatureVerifyOpResponsePayloadBenchmarkSubject
    extends KmipBenchmarkSubject<SignatureVerifyOpResponsePayload> {

  @Getter
  private final KmipSpec spec = KmipSpec.V2_1;

  public SignatureVerifyOpResponsePayloadBenchmarkSubject() throws Exception {
    SignatureVerifyOpResponsePayload subject = SignatureVerifyOpResponsePayload
        .builder()
        .uniqueIdentifier(UniqueIdentifier.of("test-uid"))
        .build();
    initialize(subject, SignatureVerifyOpResponsePayload.class);
  }

  @Override
  public String name() {
    return "SignatureVerifyOpResponsePayload";
  }
}