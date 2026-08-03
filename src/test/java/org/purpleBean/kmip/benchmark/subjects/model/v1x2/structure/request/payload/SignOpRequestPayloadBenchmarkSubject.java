package org.purpleBean.kmip.benchmark.subjects.model.v1x2.structure.request.payload;

import lombok.Getter;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purpleBean.kmip.model.core.type.DataByteString;
import org.purpleBean.kmip.model.core.type.UniqueIdentifier;
import org.purpleBean.kmip.model.v1x2.structure.request.payload.SignOpRequestPayload;

public class SignOpRequestPayloadBenchmarkSubject
    extends KmipBenchmarkSubject<SignOpRequestPayload> {

  @Getter
  private final KmipSpec spec = KmipSpec.V1_2;

  public SignOpRequestPayloadBenchmarkSubject() throws Exception {
    SignOpRequestPayload subject = SignOpRequestPayload
        .builder()
        .uniqueIdentifier(UniqueIdentifier.of("123e4567-e89b-12d3-a456-426614174000"))
        .data(DataByteString.of(new byte[] {1, 2, 3}))
        .build();
    initialize(subject, SignOpRequestPayload.class);
  }

  @Override
  public String name() {
    return "SignOpRequestPayload";
  }
}
