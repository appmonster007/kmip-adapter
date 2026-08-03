package org.purplebean.kmip.benchmark.subjects.model.v2x1.structure.request.payload;

import lombok.Getter;
import org.purplebean.kmip.api.KmipSpec;
import org.purplebean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purplebean.kmip.model.core.enumeration.CryptographicAlgorithm;
import org.purplebean.kmip.model.core.structure.CryptographicParameters;
import org.purplebean.kmip.model.v2x1.structure.request.payload.HashOpRequestPayload;

public class HashOpRequestPayloadBenchmarkSubject
    extends KmipBenchmarkSubject<HashOpRequestPayload> {

  @Getter
  private final KmipSpec spec = KmipSpec.V2_1;

  public HashOpRequestPayloadBenchmarkSubject() throws Exception {
    HashOpRequestPayload subject = HashOpRequestPayload
        .builder()
        .cryptographicParameters(CryptographicParameters
            .builder()
            .cryptographicAlgorithm(CryptographicAlgorithm.Standard.AES.inst())
            .build())
        .build();
    initialize(subject, HashOpRequestPayload.class);
  }

  @Override
  public String name() {
    return "HashOpRequestPayload";
  }
}