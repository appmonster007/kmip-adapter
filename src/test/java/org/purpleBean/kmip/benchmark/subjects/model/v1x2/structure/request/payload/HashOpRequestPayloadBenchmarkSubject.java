package org.purpleBean.kmip.benchmark.subjects.model.v1x2.structure.request.payload;

import lombok.Getter;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purpleBean.kmip.model.core.structure.CryptographicParameters;
import org.purpleBean.kmip.model.core.type.DataByteString;
import org.purpleBean.kmip.model.v1x2.structure.request.payload.HashOpRequestPayload;

public class HashOpRequestPayloadBenchmarkSubject
    extends KmipBenchmarkSubject<HashOpRequestPayload> {

  @Getter
  private final KmipSpec spec = KmipSpec.V1_2;

  public HashOpRequestPayloadBenchmarkSubject() throws Exception {
    HashOpRequestPayload subject = HashOpRequestPayload
        .builder()
        .cryptographicParameters(CryptographicParameters
            .builder()
            .build())
        .data(DataByteString.of(new byte[] {1, 2, 3}))
        .build();
    initialize(subject, HashOpRequestPayload.class);
  }

  @Override
  public String name() {
    return "HashOpRequestPayload";
  }
}
