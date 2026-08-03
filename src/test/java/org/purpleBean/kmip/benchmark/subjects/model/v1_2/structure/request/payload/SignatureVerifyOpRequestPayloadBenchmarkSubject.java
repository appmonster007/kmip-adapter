package org.purpleBean.kmip.benchmark.subjects.model.v1_2.structure.request.payload;

import lombok.Getter;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purpleBean.kmip.model.core.type.DataByteString;
import org.purpleBean.kmip.model.core.type.SignatureData;
import org.purpleBean.kmip.model.core.type.UniqueIdentifier;
import org.purpleBean.kmip.model.v1_2.structure.request.payload.SignatureVerifyOpRequestPayload;

public class SignatureVerifyOpRequestPayloadBenchmarkSubject
    extends KmipBenchmarkSubject<SignatureVerifyOpRequestPayload> {

  @Getter
  private final KmipSpec spec = KmipSpec.V1_2;

  public SignatureVerifyOpRequestPayloadBenchmarkSubject() throws Exception {
    SignatureVerifyOpRequestPayload subject = SignatureVerifyOpRequestPayload
        .builder()
        .uniqueIdentifier(UniqueIdentifier.of("123e4567-e89b-12d3-a456-426614174000"))
        .data(DataByteString.of(new byte[] {1, 2, 3}))
        .signatureData(SignatureData.of(new byte[] {4, 5, 6}))
        .build();
    initialize(subject, SignatureVerifyOpRequestPayload.class);
  }

  @Override
  public String name() {
    return "SignatureVerifyOpRequestPayload";
  }
}
