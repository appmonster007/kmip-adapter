package org.purpleBean.kmip.benchmark.subjects.model.v2x1.structure.request.payload;

import lombok.Getter;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purpleBean.kmip.model.core.enumeration.CryptographicAlgorithm;
import org.purpleBean.kmip.model.core.structure.CryptographicParameters;
import org.purpleBean.kmip.model.v2x1.structure.request.payload.HashOpRequestPayload;

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