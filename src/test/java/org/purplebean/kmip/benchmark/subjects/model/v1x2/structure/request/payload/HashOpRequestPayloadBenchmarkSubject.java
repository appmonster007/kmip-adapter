package org.purplebean.kmip.benchmark.subjects.model.v1x2.structure.request.payload;

import lombok.Getter;
import org.purplebean.kmip.api.KmipSpec;
import org.purplebean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purplebean.kmip.model.core.structure.CryptographicParameters;
import org.purplebean.kmip.model.core.type.DataByteString;
import org.purplebean.kmip.model.v1x2.structure.request.payload.HashOpRequestPayload;

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
