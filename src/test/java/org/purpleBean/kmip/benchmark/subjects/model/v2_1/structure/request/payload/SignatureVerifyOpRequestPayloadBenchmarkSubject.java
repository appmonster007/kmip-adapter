package org.purpleBean.kmip.benchmark.subjects.model.v2_1.structure.request.payload;

import lombok.Getter;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purpleBean.kmip.model.core.type.UniqueIdentifier;
import org.purpleBean.kmip.model.v2_1.structure.request.payload.SignatureVerifyOpRequestPayload;

public class SignatureVerifyOpRequestPayloadBenchmarkSubject
    extends KmipBenchmarkSubject<SignatureVerifyOpRequestPayload> {

  @Getter
  private final KmipSpec spec = KmipSpec.V2_1;

  public SignatureVerifyOpRequestPayloadBenchmarkSubject() throws Exception {
    SignatureVerifyOpRequestPayload subject = SignatureVerifyOpRequestPayload
        .builder()
        .uniqueIdentifier(UniqueIdentifier.of("test-uid"))
        .build();
    initialize(subject, SignatureVerifyOpRequestPayload.class);
  }

  @Override
  public String name() {
    return "SignatureVerifyOpRequestPayload";
  }
}